package edu.msoe;

import java.util.Scanner;

/**
 * Represents complex numbers.  Objects from the class are immutable,
 * i.e., their value cannot change once they are created.
 * @author taylor
 * @version 2012.11.09_1
 */
public class Complex {
 
  /**
   * Real component of the complex number
   */
  private final double real;
 
  /**
   * Imaginary component of the complex number
   */
  private final double imag;
 
  /**
   * Determines whether the string representation of the complex number
   * will be in cartesian or polar coordinates
   */
  private static boolean isPolar = false;
 
  /**
   * Default constructor of a complex number with real and
   * imaginary components of zero
   */
  public Complex() {
    this(0.0);
  }
 
  /**
   * Constructor of a complex number with an imaginary component of zero
   * @param real The value of the real component
   */
  public Complex(double real) {
    this(real, 0.0);
  }
 
  /**
   * Constructor for a fully specified complex number
   * @param real The value of the real component
   * @param imag The value of the imaginary component
   */
  public Complex(double real, double imag) {
    this.real = real;
    this.imag = imag;
  }
 
  /**
   * Constructor that accepts a string representation of a
   * complex number
   * @param num String representation of a complex number
   */
  public Complex(String num) {
    double real = 0.0;
    double imag = 0.0;
    num = num.replace("(", "").replace(")", "");
    if(num.contains("|")) {
      // In polar form, e.g., "1 | 45"
      Scanner parser = new Scanner(num);
      double magnitude = parser.nextDouble();
      parser.next();
      double angle = Math.toRadians(parser.nextDouble());
      real = magnitude * Math.cos(angle);
      imag = magnitude * Math.sin(angle);
    } else if(num.contains(" + ") || num.contains(" - ")) {
      // Both real and imaginary components present, e.g., "2.0 - 2.0i"
      Scanner parser = new Scanner(num.substring(0, num.length()-1));
      real = parser.nextDouble();
      parser.next();
      imag = parser.nextDouble();
      if(num.contains(" - ")) {
        imag = -imag;
      }
    } else if(num.contains("i")) { 
      // imaginary component only, e.g., "3.8i"
      Scanner parser = new Scanner(num.substring(0, num.length()-1));
      imag = parser.nextDouble();
    } else {
      // real component only, e.g., "-5.6"
      Scanner parser = new Scanner(num);
      real = parser.nextDouble();
    }
    this.real = real;
    this.imag = imag;
  }
 
  /**
   * The String representation of the complex number
   * This may be in cartesian or polar form depending on
   * the value of the class variable isPolar.
   * @return String representation of the object
   */
  public String toString(){
    String answer;
    if(!isPolar) {
      if(this.imag==0.0) {
        answer = Double.toString(real);
      } else if(this.real==0.0) {
        answer = imag + "i";
      } else if(this.imag<0.0) {
        answer = "(" + real + " - " + (-imag) + "i)";
      } else {
        answer = "(" + real + " + " + imag + "i)";
      }
    } else {
      answer = "(" + getMagnitude() + " | " + getAngle() + ")";
    }
    return answer;
  }
 
  /**
   * Calculates the sum of the object and a real value
   * @param addend The value to be added to the real component of the complex number
   * @return A new complex number containing the sum of the object and the specified
   * real component
   */
  public Complex plus(double addend) {
    return new Complex(this.real + addend, this.imag);
  }
 
  /**
   * Calculates the sum of two complex numbers
   * @param num Number to be added
   * @return the result of the sum of two complex numbers
   */
  public Complex plus(Complex addend) {
    return new Complex(this.real + addend.real, this.imag + addend.imag);
  }
 
  /**
   * Calculates the difference of two complex numbers
   * @param subtrahend Number to be subtracted
   * @return the result of taking away the specified value from the object 
   */
  public Complex minus(Complex subtrahend) {
    return new Complex(real - subtrahend.real, imag - subtrahend.imag);
  }
 
  /**
   * Compares two complex numbers to see if they are equal
   * @param that The complex number to compare
   * @return true if the objects share the same value, otherwise false
   */
  public boolean equals(Complex that) {
    return this.real==that.real && this.imag==that.imag;
  }
 
  /**
   * Returns the magnitude of the complex number
   * @return the magnitude of the complex number
   */
  public double getMagnitude() {
    return Math.sqrt(Math.pow(real, 2) + imag*imag);
  }
 
  /**
   * Returns the angle of the complex number in degrees
   * @return the angle of the complex number in degrees
   */
  public double getAngle() {
    return Math.toDegrees(Math.atan(imag/real));
  }
 
  /**
   * Sets class preference so that complex numbers are represented in
   * polar coordinates
   */
  public static void setPolar() {
    isPolar = true;
  }
 
  /**
   * Sets class preference so that complex numbers are represented in
   * cartesian coordinates
   */
  public static void setCartesian() {
    isPolar = false;
  }
 
  /**
   * Calculates the product of two complex numbers
   * @param multiplicand Number to be multiplied
   * @return the result of the product of two complex numbers
   */
  public Complex times(Complex multiplicand){
    return new Complex(this.real * multiplicand.real - imag * multiplicand.imag, 
        imag * multiplicand.real + real * multiplicand.imag);
  }
 
  /**
   * Calculates the result of dividing the passed complex number
   * into the calling number
   * @param divisor Number to be used as the divisor
   * @return the result of the division
   */
  public Complex dividedBy(Complex divisor){
    double magnitude = getMagnitude() / divisor.getMagnitude();
    double angle = Math.toRadians(getAngle() - divisor.getAngle());
    return new Complex(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
  }
 
}