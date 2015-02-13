package edu.msoe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Scanner;

public class Lecture16 extends JFrame {

    class Map extends JPanel implements Serializable {
        public static final int BOARD_WIDTH = 60;
        public static final int BOARD_HEIGHT = 40;

        private static final long serialVersionUID = 5213846318516416L;
        private Tile[][] board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];



        public Tile[][] getBoard() {
            return board;
        }

        private void addTile(int x, int y, Tile pixel) {
            this.board[x][y] = pixel;
        }

        public void randomize() {
            Random rand = new Random();

            for (int x = 0; x < BOARD_WIDTH; x++) {
                for (int y = 0; y < BOARD_HEIGHT; y++) {
                    addTile(x, y, new Tile(0, rand.nextInt(256), 0));
                }
            }
        }

        public void paintComponent (Graphics g) {
            super.paintComponent(g);

            for (int x = 0; x < BOARD_WIDTH; x++) {
                for (int y = 0; y < BOARD_HEIGHT; y++) {
                    Tile tile = board[x][y];
                    tile.draw(x*Tile.TILE_WIDTH, y*Tile.TILE_HEIGHT, g);
                }
            }

        }

    }

    class Tile implements Serializable {
        private static final long serialVersionUID = 5213846318516416L;
        public static final  int TILE_WIDTH = 10;
        public static final  int TILE_HEIGHT = 10;
        private Color color;


        private Tile(){}

        public Tile(int r, int g, int b) {
            this.color = new Color(r, g, b);
        }

        public Color getColor() {
            return this.color;
        }

        public void draw(int x, int y, Graphics g) {
            g.setColor(this.color);
            double r = Math.random();
            if (r < .25) {
                g.fillRect(x,y,10,10);
            } else if (r >= 0.25 && r < 0.50) {
                g.drawLine(x, y, x + 10, y);
            } else if (r >= 0.5 && r < 0.75) {
                g.drawLine(x,y,x,y + 10);
            }


        }

        public String toString() {
            return "" + color.getRGB();
        }
    }



    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                Lecture16 window = new Lecture16();
                window.showWindow();
            }
        });
    }

    public void showWindow() {
        setMinimumSize(new Dimension(600,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack();

        setLayout(new BorderLayout());

        Map map  = new Map();
        map.randomize();;

        add(map, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        JButton randomizeButton = new JButton("Randomize");
        randomizeButton.addActionListener(new ActionListener(){

            private Map map;
            @Override
            public void actionPerformed(ActionEvent e){
                map.randomize();
                repaint();
            }

            private ActionListener initialize(Map map) {
                this.map = map;
                return this;
            }
        }.initialize(map));




        JButton saveBytesButton = new JButton("Save Bytes");
        saveBytesButton.addActionListener(new MapSaveBytesListener(map));


        JButton saveDataButton = new JButton("Save Data");
        saveDataButton.addActionListener(new MapSaveDataListener(map));

        JButton saveTextButton = new JButton("Save Text");
        saveTextButton.addActionListener(new MapSaveTextListener(map));

        JButton saveTilesButton = new JButton("Save Tiles");
        saveTilesButton.addActionListener(new MapSaveTilesListener(map));

        JButton dumpBytesButton = new JButton("Dump Bytes");
        dumpBytesButton.addActionListener(new MapDumpBytesListener());


        JButton dumpDataButton = new JButton("Dump Data");
        dumpDataButton.addActionListener(new MapDumpDataListener());

        JButton dumpTextButton = new JButton("Dump Text");
        dumpTextButton.addActionListener(new MapDumpTextListener());

        JButton dumpTilesButton = new JButton("Dump Tiles");
        dumpTilesButton.addActionListener(new MapDumpTilesListener());

        buttons.add(randomizeButton);

        buttons.add(saveBytesButton);
        buttons.add(saveDataButton);
        buttons.add(saveTextButton);
        buttons.add(saveTilesButton);


        buttons.add(dumpBytesButton);
        buttons.add(dumpDataButton);
        buttons.add(dumpTextButton);
        buttons.add(dumpTilesButton);

        add(buttons, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class MapSaveTilesListener implements ActionListener {
        private Map map;

        public MapSaveTilesListener(Map map) {
            this.map = map;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Tile[][] board = map.getBoard();

            File outFile = new File("saved.map");

            try (FileOutputStream out = new FileOutputStream(outFile, false);
                 ObjectOutputStream object = new ObjectOutputStream(out)) {

                for (int x = 0; x < board.length; x++) {
                    for (int y = 0; y < board[0].length; y++) {
                        object.writeInt(x);
                        object.writeInt(y);
                        object.writeObject(board[x][y]);
                    }
                }
                System.out.println("Data written as serialized objects.");
            } catch (FileNotFoundException ex) {
                System.err.println("File not found");
                System.err.println(ex.getMessage());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }
    private class MapDumpTilesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try (FileInputStream stream = new FileInputStream("saved.map");
                 ObjectInputStream in  = new ObjectInputStream(stream)) {

                while (in.available() > 0) {
                    int x = in.readInt();
                    int y = in.readInt();
                    Tile t = (Tile)in.readObject();
                    System.out.println("" + x + " " + y + " " + t);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("'saved.map' does not exist.");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Tile class not found.");
            }
        }
    }

    private class MapSaveTextListener implements ActionListener {
        private Map map;

        public MapSaveTextListener(Map map) {
            this.map = map;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Tile[][] board = map.getBoard();

            File outFile = new File("saved.map");

            try (PrintWriter out = new PrintWriter(outFile)) {

                for (int x = 0; x < board.length; x++ ) {
                    for (int y = 0; y < board[0].length; y++) {
                        out.print(x + ",");
                        out.print(y + ",");
                        out.println(board[x][y].getColor().getRGB());
                    }
                }
                System.out.println("Data written as text.");
            }
            catch(FileNotFoundException ex) {
                System.err.println("File not found");
                System.err.println(ex.getMessage());
            }
        }

    }
    private class MapDumpTextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try (Scanner in = new Scanner(new FileInputStream("saved.map"))) {

                while (in.hasNext()) {
                    String token = in.nextLine();
                    System.out.println(token);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("'saved.map' does not exist.");
            }
        }
    }

    private class MapSaveDataListener implements ActionListener {
        private Map map;

        public MapSaveDataListener(Map map) {
            this.map = map;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Tile[][] board = map.getBoard();

            File outFile = new File("saved.map");

            try (FileOutputStream out = new FileOutputStream(outFile, false)) {
                DataOutputStream data = new DataOutputStream(out);

                for (int x = 0; x < board.length; x++ ) {
                    for (int y = 0; y < board[0].length; y++) {
                        data.writeInt(x);
                        data.writeInt(y);
                        data.writeInt(board[x][y].getColor().getRGB());
                    }
                }
                System.out.println("Data written as data.");
            }
            catch(FileNotFoundException ex) {
                System.err.println("File not found");
                System.err.println(ex.getMessage());
            }
            catch(IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }
    private class MapDumpDataListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try (FileInputStream in = new FileInputStream("saved.map")) {
                DataInputStream data = new DataInputStream(in);

                while (data.available() > 0) {
                    int x = data.readInt();
                    int y = data.readInt();
                    int c = data.readInt();
                    //Color color = new Color(c);
                    //String hex = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
                    //System.out.println("(" + x + ", " + y + ") Color: " + hex);
                    System.out.println(x + " " + y + " " + c);

                }
            } catch (FileNotFoundException ex) {
                System.out.println("'saved.map' does not exist.");
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
    }

    private class MapSaveBytesListener implements ActionListener {

        private Map map;

        public MapSaveBytesListener(Map map) {
            this.map = map;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Tile[][] board = map.getBoard();

            File outFile = new File("saved.map");

            try (FileOutputStream out = new FileOutputStream(outFile, false)) {
                for (int x = 0; x < board.length; x++ ) {
                    for (int y = 0; y < board[0].length; y++) {
                        ByteBuffer buffer = ByteBuffer.allocate(12);
                        buffer.putInt(x);
                        buffer.putInt(y);
                        buffer.putInt(board[x][y].getColor().getRGB());

                        out.write(buffer.array());
                    }
                }
                System.out.println("Data written as bytes.");
            }
            catch(FileNotFoundException ex) {
                System.err.println("File not found");
                System.err.println(ex.getMessage());
            }
            catch(IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    private class MapDumpBytesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try (FileInputStream in = new FileInputStream("saved.map")) {

                while (in.available() > 0) {
                    byte[] buffer = new byte[5];
                    in.read(buffer);

                    for(int i = 0; i < buffer.length; i++) {
                        char c = (char) buffer[i];
                        System.out.print(c);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("'saved.map' does not exist.");
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
    }
}
