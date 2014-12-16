package edu.msoe;

/**
 * Created by dennis on 12/15/2014.
 */
public class Lecture06 {

    public static void main(String[] args) {


        /* Reference Types vs. Object Types  && Automatic Type Promotion */

        //Object aObject = new Lecture06().new Person();
        //Person aPerson = new Lecture06().new Person();
        //Student aStudent = lecture.new Person();


    }


    class Person {

        private String name = "";
        public Person(){}

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {

            return (o instanceof Lecture06.Person)
                    && this.getName().equals(((Person) o).getName());

        }


    }

    class Student extends Person {

        private String year = "";
        public Student(){}


        public void setYear(String year) {
            this.year = year;
        }

        public String getYear() {
            return this.year;
        }

        @Override
        public boolean equals(Object o) {

            return  (o instanceof Lecture06.Student)
                    && this.getName().equals(((Student)o).getName())
                    && this.getYear().equals(((Student)o).getYear());
        }


    }
}
