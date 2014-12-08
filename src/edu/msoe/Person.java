package edu.msoe;

/**
 * Created by dennis on 12/5/2014.
 */
public class Person {


    private String firstName;
    private String lastName;

    public Person(){
        System.out.println("Person default constructor");
    }

    public Person(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    @Override
    public String toString() {
        return "I am a Person and my name is " + this.getFirstName() + " " + this.getLastName();
    }


    public static Person create(String firstName) {

        Person p = new Person(firstName, "");
        return p;
    }
}
