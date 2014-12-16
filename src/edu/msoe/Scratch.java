package edu.msoe;

/**
 * Created by dennis on 12/10/2014.
 */
public class Scratch {


    public static void main(String[] args) {
        int j=1;

        int total = 0;

        for(int i = 0; i < 8 ;i++){

            total+= (i+j);

            i = j;

            j = total;
            System.out.println(total + "");

        }

    }
}
