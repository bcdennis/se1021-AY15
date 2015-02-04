package edu.msoe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dennis on 12/10/2014.
 */
public class Scratch {


    public static void main(String[] args) {

    }

    public void test()
    {
        int[] values = new int[10];

        Scanner standardIn = new Scanner(System.in);

        String listInput   = "";
        String numeratorInput = "";
        String divisorInput = "";

        System.out.println("Enter ten integers:");
        for (int i = 0; i < 10; i++) {
            listInput = standardIn.nextLine();

            values[i] = Integer.parseInt(listInput);
        }


        System.out.println("Enter the index for the numerator: ");
        numeratorInput = standardIn.nextLine();

        System.out.println("Enter the index for the divisor: ");
        divisorInput = standardIn.nextLine();

        int numeratorIndex = Integer.parseInt(numeratorInput);
        int divisorIndex = Integer.parseInt(divisorInput);

        int numerator =values[numeratorIndex];
        int divisor = values[divisorIndex];

        System.out.println("The answer is: ");
        System.out.println(numerator/divisor);
    }
}
