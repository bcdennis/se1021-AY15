package edu.msoe;

import javax.swing.*;

public class Lecture02 {

  /**
   * Main method for the lecture scratch file.
   * 
   * @param args
   */
  public static void main(String[] args) {

    // String response = JOptionPane.showInputDialog(null, "Enter something:");

    manipulateCharacters();
    typeCasting();
    incrementors();

  }

  private static void manipulateCharacters() {
    // consider
    char variableA = 'A';
    char variableB = 'B';
    char variableC = 'C';

    // What do you think will print here?
    System.out.println(variableA - variableB);

    // What do you think will print?
    System.out.println("Now I know my " + variableA + variableB + variableC + "'s.");

    
    // What about now?
    System.out.println(variableA + variableB + variableC + "'s is what I now know.");
    System.out.println((char)65 + (char)66 + (char)67 + "'s is what I now know.");

    System.out.println("Now I know my " + (char)65 + (char)66 + (char)67 + "'s.");

    
    /*
     * 
     * 
     * This is called 'promotion'.  The character is promoted to a String, automatically.
     * Why doesn't Java allow 'demotion'?
     * 
     * 
     */
  }
  
  private static void typeCasting()
  {
    float budget = 1.1567e3f;
    float budget2 = 1156.7f;
    double budget3 = 1156.7;
    
    System.out.println(budget3);
    
    System.out.println(budget);

    // What happens here?
    System.out.println((double)budget);

    // Or here?
    System.out.println(budget == (double)budget);
    
    //What about this?
    System.out.println(budget2 == (double)budget);

    
    // What do you think will happen here?
    //budget2 = 1100.0;
    
    // What will this produce?
    System.out.println((int)budget);
  }
  
  private static void incrementors()
  {
    int a = 0;
    int b = 0;
    
    // What will this sequence be?
    System.out.println(a++);
    System.out.println(a);
    System.out.println(a--);
    System.out.println(a);
    
    
    // What will this sequence be?
    System.out.println(++b);
    System.out.println(b);
    System.out.println(--b);
    System.out.println(b);
    
  }

}
