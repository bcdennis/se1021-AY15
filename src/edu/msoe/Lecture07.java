package edu.msoe;

/**
 * Created by dennis on 12/15/2014.
 */
public class Lecture07 {

    public static void main(String[] args) {



        /*
        Object aObject = new Lecture07().new Animal();
        aObject.setName("Hobbes");

        System.out.println(aObject.speak());
        System.out.println(aObject);
        */

/*
        Animal anAnimal = new Lecture07().new Cat();
        anAnimal.setName("Tabby");

        System.out.println(anAnimal.speak());
        System.out.println(anAnimal);



        Cat aCat = new Lecture07().new Cat();
        aCat.setName("Tabby");

        System.out.println(aCat.speak());
        System.out.println(aCat);
        System.out.println(aCat.equals(anAnimal));

  */

        Dog aDog = new Lecture07().new Dog();
        aDog.setName("Fido");

        System.out.println(aDog.speak());
        System.out.println(aDog);

        Dog anotherDog = new Lecture07().new Dog();
        anotherDog.setName("Fido");

        System.out.println(anotherDog.speak());
        System.out.println(anotherDog);
        System.out.println(anotherDog.equals(aDog));

    }

    //Why are these always public?
    interface Petable {
        String pet();
    }

    abstract class Animal {

        private String name = "";
        protected String nickname = "";

        public Animal(){}

        public void setName(String name) {
            this.name = name;
            this.nickname = name;
        }

        public String getName() {
            return this.name;
        }

        public String getNickName() {
            return this.nickname;
        }

        public abstract String speak();

        @Override
        public boolean equals(Object obj) {

            return (obj != null)
                    && (obj instanceof Lecture07.Animal)
                    && this.getName().equals(((Animal) obj).getName());
        }

        @Override
        public String toString() {
            return "My name is: " + this.getName() + " and I go '" + this.speak() + "'.";
        }
    }

    class Cat extends Animal implements Petable {

        public Cat(){}


        @Override
        public void setName(String name) {
            super.setName(name);
            //Why can I do this for nickname but not name?
            this.nickname = name + "-ee the Cat";
        }

        public String speak() {
            return "Meow Meow";
        }

        public String pet() {
            return "Purrrr";
        }

        @Override
        public String toString() {
            return super.toString()
                    + " That makes me a cat."
                    + " When I am petted I go '" + this.pet() + "'."
                    + " My nickname is '" + this.getNickName() + "'.";
        }

    }

    class Dog extends Animal implements Petable {

        public Dog(){}


        public String speak() {
            return "Woof woof";
        }

        public String pet() {
            return "*tap* *tap* *tap*";
        }

        @Override
        public String toString() {
            return super.toString()
                    + " That makes me a dog."
                    + " When I am petted I go '" + this.pet() + "'."
                    + " My nickname is '" + this.getNickName() + "'.";
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj) && (obj instanceof Lecture07.Dog);
        }
    }
}
