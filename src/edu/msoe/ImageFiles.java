package edu.msoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFiles extends JFrame {

    private JPanel canvas;


    public static void main(String[] args) {

        ImageFiles imgFiles = new ImageFiles();

        imgFiles.files();

    }

    private void files() {

        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new JPanel();

        add(canvas);


        // http://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html
        JFileChooser imageChooser = new JFileChooser();

        imageChooser.setFileFilter(new FileNameExtensionFilter("JPEG File", "jpg"));
        imageChooser.setFileFilter(new FileNameExtensionFilter("PNG File", "png"));
        imageChooser.setFileFilter(new FileNameExtensionFilter("PPM File", "ppm"));
        imageChooser.setAcceptAllFileFilterUsed(false);

        int returnValue = imageChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // http://docs.oracle.com/javase/8/docs/api/java/io/File.html
            File selectedImageFile = imageChooser.getSelectedFile();


            System.out.println("You selected: \n" + selectedImageFile.getName());
            System.out.println("It is located: \n" + selectedImageFile.getPath());
            System.out.println(selectedImageFile.getAbsolutePath());

            /* Sidebar: Path vs. Absolute Path vs. Canonical
             *
             * C:\IdeaProjects\se1021\exceptions.log        - a path, an absolute path, and a canonical path
             * .\exceptions.log                             - a path, but not absolute nor canonical
             * C:\IdeaProjects\se1021\bin\..\exceptions.log - a path, an absolute path, but not canonical.
             */

            String extension = parseExtension(selectedImageFile.getName());

            System.out.println("The file extension is:\n" + extension);
            System.out.println("The file filter type was:\n" + imageChooser.getFileFilter().getDescription());

            BufferedImage img = null;

            try {
                img = ImageIO.read(selectedImageFile);
                JLabel imageLabel = new JLabel(new ImageIcon(img));
                canvas.add(imageLabel);

                Dimension dim = new Dimension(img.getWidth(), img.getHeight());

                // inform the layout manager not to mess with size of this panel.
                canvas.setSize(dim);
                canvas.setMaximumSize(dim);
                canvas.setMinimumSize(dim);
                canvas.setPreferredSize(dim);
                setSize(dim);


            } catch (IOException e) {
                System.out.println("Couldn't load image.  Aborting...");
                System.exit(1);
            }

        }

        setVisible(true);
    }

    private String parseExtension(String filename) {
        String[] parts = filename.split("\\.");
        String extension = "";

        for(int i = 0; i < parts.length; i++){
            System.out.println(parts[i]);
        }

        if (parts.length > 0) {
            extension = parts[parts.length -1];
        }

        return extension;
    }

    private String getExtensionFromFilterDescription(String filterDescription) {
        String extension  = "";

        if (filterDescription.equals("JPEG File")) {
            extension = "jpg";
        } else if (filterDescription.equals("PNG File")) {
            extension = "png";
        } else if (filterDescription.equals("PPM File")) {
            extension = "ppm";
        } else if (filterDescription.equals("All Files")) {
            extension = "unknown";
        }

        return extension;
    }
}
