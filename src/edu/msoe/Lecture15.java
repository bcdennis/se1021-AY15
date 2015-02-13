package edu.msoe;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lecture15 {

    public static void main(String[] args){

        //fileClass();

        //streams();

        streamsTryWithResources();

        //highlevel();

    }

    public static void fileClass() {


        // a file in the current directory
        File aFile = new File("file-does-not-exist.txt");

        if (!aFile.exists()) {
            System.out.println("'file-does-not-exist.txt' does not exist.");
        }


        // a file in a specific directory
        aFile = new File("C:/IdeaProjects/se1021/a-file-that-exists.txt");

        if (!aFile.exists()) {
            System.out.println("'C:/IdeaProjects/se1021/a-file-that-exists.txt' does not exist.");
        }

        // a directory itself
        aFile = new File("C:/IdeaProjects/se1021");
        if (!aFile.exists()) {
            System.out.println("'C:/IdeaProjects/se1021' does not exist.");
        } else if (aFile.isDirectory()) {
            for (String filesystemObject : aFile.list()) {
                System.out.println(filesystemObject);
            }
        }


    }

    public static void streams() {

        FileInputStream in = null;

        try{
            in = new FileInputStream("cheeseburger.png");
/*
            while (in.available() > 0) {
                char c = (char)in.read();
                System.out.print(c);
            }
*/
            //in.reset();

            in = new FileInputStream("cheeseburger.png");


            while (in.available() > 0) {
                byte[] buffer = new byte[5];
                in.read(buffer);

                for(int i = 0; i < buffer.length; i++) {
                    char c = (char) buffer[i];
                    System.out.print(c);
                }
            }



        } catch (FileNotFoundException ex) {
            System.out.println("'cheeseburger.png' does not exist.");
        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    System.out.println("Error closing file.");

                }
            }

        }


    }

    public static void streamsTryWithResources() {
        try (FileInputStream in = new FileInputStream("cheeseburger.png")) {


            while (in.available() > 0) {
                byte[] buffer = new byte[5];
                in.read(buffer);

                for(int i = 0; i < buffer.length; i++) {
                    char c = (char) buffer[i];
                    System.out.print(c);
                }
            }


        } catch (FileNotFoundException ex) {
            System.out.println("'cheeseburger.png' does not exist.");
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

    public static void highlevel() {
       /* try (Scanner in = new Scanner("cheeseburger.png")) {
            while (in.hasNext()) {
               char c = (char) in.nextByte();
               System.out.print(c);

            }
        } catch (InputMismatchException ex) {
            ex.printStackTrace();
        }*/

        try (Scanner in = new Scanner(new FileInputStream("a-file-that-exists.txt"))) {

            while (in.hasNext()) {
                String token = in.nextLine();
                System.out.println(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("'cheeseburger.png' does not exist.");
        }


    }
}
