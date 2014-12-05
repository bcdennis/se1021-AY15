package edu.msoe;


public class Lecture03 {


    public static void main(String[] args) {

        // This series adapted from:
        // http://msoe.us/taylor/tutorial/se1011/

        Lecture03.ShortCircuits();
        //Lecture03.Inheritance();
        boolean c = false;

        System.out.println(c);

    }

    public static void ShortCircuits(){

        Person me = null;


        if ((me != null) || (me.getFirstName().equals("Dr."))) {

            System.out.println("The person was Dr. Dennis.");
        }

/*
        Person you;

        if (you.getFirstName().equals("Dr. Dennis")) {
            System.out.println("The person was Dr. Dennis.");
        }
*/

    }

    public static void Inheritance() {

        Professor me = new Professor("The Prof");
        me = new Professor();
        me.teachClass();
    }


}
