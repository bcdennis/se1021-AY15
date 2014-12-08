package edu.msoe;

/**
 * Created by dennis on 12/5/2014.
 */
public class Professor extends Person{
    private String department;
    private String nickName;
    private String title = "Dr. ";

    public Professor(){

    }

    public Professor(String nickName) {
        this.setNickName(nickName);
    }

    public Professor(String firstName, String lastName){
        //this();
        super(firstName, lastName);

    }

    @Override
    public String getFirstName() {
        return this.title + super.getFirstName();
    }

    @Override
    public String toString() {
        return super.toString() + "  I am also a Professor.";
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setNickName(String nickname) {
        this.nickName = nickname;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void teachClass() {
        System.out.println(this.getNickName());
        System.out.println("Blah blah... zzzzzz");
    }

    public static Person create(String firstName) {
        Person p = new Professor(firstName, "");

        return p;
    }
}
