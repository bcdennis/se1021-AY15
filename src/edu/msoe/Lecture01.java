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

    Lecture01.Primitives();
    //Lecture01.References();
    //Lecture01.PrimitivesVReferences();
    // Lecture01.Functional();

  }

  private static void Primitives() {

    // consider
    int number;
    number = 5;
    number = number + 3;

    // How is this stored in memory?
    System.out.println(number);

    // Integer types (Think, Pair & Share)
    // From smallest to largest what are Java's integer types?  
    // How much storage does each require? 
    
    // Floating point (Think, Pair & Share)
    // From smallest to largest what are Java's floating point types?
    // How much storage does each require?
    

  }

  private static void References() {
    // consider
    String phrase;
    phrase = "that makes sense";

    // How is this stored in memory?
    System.out.println(phrase);
  }

  private static void PrimitivesVReferences() {
    // consider
    int number = 5;
    int number2 = number;

    number = 10;

    // What does 'number2' equal to?
    System.out.println(number2);

    // now consider
    String phrase = "that makes sense";
    String phrase2 = phrase;

    // What is phrase2
    System.out.println(phrase2);

    // now consider
    String phrase3 = "that makes sense";

    // What do you think this will be?
    System.out.println(phrase2 == phrase3);
  }

  private static void Functional() {
    /*
     * Suppose we want a program that asks the user to enter two complex numbers and displays the
     * result of multiplying the them together.
     */

    Scanner in = new Scanner(System.in);
    // Get first number
    System.out.println("Enter a complex number in the form: 3.0 + 4.3i");
    String line = in.nextLine();
    Scanner parser = new Scanner(line.substring(0, line.length() - 1));
    double realOne = parser.nextDouble();
    parser.next();
    double imagOne = parser.nextDouble();

    // Get second number
    System.out.println("Enter a complex number in the form: 3.0 + 4.3i");
    line = in.nextLine();

    parser.close();

    parser = new Scanner(line.substring(0, line.length() - 1));
    double realTwo = parser.nextDouble();
    parser.next();
    double imagTwo = parser.nextDouble();

    // Calculate result of multiplying two numbers
    double realAnswer = realOne * realTwo - imagOne * imagTwo;
    double imagAnswer = realOne * imagTwo + imagOne * realTwo;

    System.out.println("(" + realOne + " + " + imagOne + "i) * (" + realTwo + " + " + imagTwo
        + "i) = (" + realAnswer + " + " + imagAnswer + "i)");

    parser.close();
    in.close();

    // DRY Princple
  }

}
