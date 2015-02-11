package edu.msoe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Lecture16 extends JFrame {

    class Map extends JPanel implements Serializable {
        public static final int BOARD_WIDTH = 60;
        public static final int BOARD_HEIGHT = 40;

        private static final long serialVersionUID = 5213846318516416L;
        private Tile[][] board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];



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
            g.fillRect(x,y,10,10);

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
        setResizable(false);
        pack();

        setLayout(new BorderLayout());

        Map map  = new Map();
        map.randomize();;

        add(map, BorderLayout.CENTER);

        JPanel buttons = new JPanel();

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

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new MapSaveListener(map));

        buttons.add(randomizeButton);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class MapSaveListener implements ActionListener {

        private Map map;

        public MapSaveListener(Map map) {
            this.map = map;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            map.randomize();
            repaint();
        }
    }

}
