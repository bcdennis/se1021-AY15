package edu.msoe;

/**
 * Created by dennis on 12/8/2014.
 */
public class Lecture04 {

    public static void main(String[] args) {

        // This series adapted from:
        // http://msoe.us/taylor/tutorial/se1011/

        Lecture04.MethodHiding();
        Lecture04.MethodOverriding();

        // Relationships: Aggregation vs. Composition vs. Association vs. Inheritance
        /*
            Object 'A' made-of Object 'B'  - (Composition) B has no meaning or purpose in the system without A.
                                                - A "owns" B.
                                                - Matches the Owner's lifetime.
            Object 'A' has-a Object 'B'  - (Aggregation) B exists independently from A.
                                                - A "owns" B.
                                                - Independent lifetime.
            Object 'A' uses Object 'B'  - (Association) B exists independently from A.
                                                - No owner.
                                                - Independent lifetime.
            Object 'A' is-a Object 'B'  (Inheritance) A is a derived, specialized form of B.

         */

    }


    public static void MethodHiding() {

        Person A = Person.create("Brad");
        Person B = Professor.create("Brad");

        System.out.println(A.getFirstName());
        System.out.println(B.getFirstName());
    }

    public static void MethodOverriding() {
        Person A = Person.create("Brad");
        Person B = Professor.create("Brad");

        System.out.println(A);
        System.out.println(B);
    }

}
