/**
 * 
 */
package edu.msoe;

import java.util.Scanner;

/**
 * @author Brad
 * 
 */
public class Lecture01 {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
        // This series adapted from:
        // http://msoe.us/taylor/tutorial/se1011/
        
        //Lecture01.Primitives();
        //Lecture01.References();
        //Lecture01.PrimitivesVReferences();
        //Lecture01.Functional();
        Lecture01.RefactoredStep1();
        
    }
    
    private static void Primitives() {
        
        // consider
        int number;
        number = 5;
        number = number + 3;
        

        
        // How is this stored in memory?
        
        /**
         * There are many primitives in Java:
         * int     - storage: 4 bytes 
         *            domain:  -2,147,483,648 and 2,147,483,647
         * double  - storage: 8 bytes 
         *            domain:  -1.79769313486231570 x 10^308 and 1.79769313486231570 x 10^308
         * char    - storage: 2 bytes
         *            domain: Unicode character set
         * boolean - storage: JVM dependent.                 
         */
        
        System.out.println(number);
        
    }
    
    private static void References() {
        // consider
        String phrase;
        phrase = "that makes sense";
        
        // How is this stored in memory?
        
        System.out.println(phrase);
    }
    
    private static void PrimitivesVReferences() {
        //consider
        int number = 5;
        int number2 = number;
        
        number = 10;
        
        //What does 'number2' equal to?
        System.out.println(number2);
        
        
        //now consider
        String phrase = "that makes sense";
        String phrase2 = phrase;
        
        //What is phrase2
        System.out.println(phrase2);
        
        //now consider
        String phrase3 = new String("that makes sense");
        
        //What do you think this will be?
        System.out.println(phrase2 == phrase3);
    }
    
    private static void Functional() {
        /*
         * Suppose we want a program that asks the user to enter 
         * two complex numbers and displays the result of multiplying 
         * the them together.
         */
        
        Scanner in = new Scanner(System.in);
        // Get first number
        System.out.println("Enter a complex number in the form: 3.0 + 4.3i");
        String line = in.nextLine();
        Scanner parser = new Scanner(line.substring(0, line.length()-1));
        double realOne = parser.nextDouble();
        parser.next();
        double imagOne = parser.nextDouble();
       
        // Get second number
        System.out.println("Enter a complex number in the form: 3.0 + 4.3i");
        line = in.nextLine();
        
        parser.close();
        
        parser = new Scanner(line.substring(0, line.length()-1));
        double realTwo = parser.nextDouble();
        parser.next();
        double imagTwo = parser.nextDouble();
       
        // Calculate result of multiplying two numbers
        double realAnswer = realOne * realTwo - imagOne * imagTwo;
        double imagAnswer = realOne * imagTwo + imagOne * realTwo;
       
        System.out.println("(" + realOne + " + " + imagOne + "i) * (" 
                           + realTwo + " + " + imagTwo + "i) = ("
                           + realAnswer + " + " + imagAnswer + "i)");
        
        parser.close();
        in.close();
        
        // DRY Princple
   }
    
    public static void RefactoredStep1() {
        Complex1 c1 = new Complex1(2.0, 1.0);
        // What do you think will print out here?
        System.out.println(c1);
        
    }
   
    
    static class Complex1 {
        private double real;
        private double imag;
        
        public Complex1(double r, double i) {
            real = r;
            imag = i;
        }
    }

}
