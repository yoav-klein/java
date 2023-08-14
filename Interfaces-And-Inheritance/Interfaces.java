
interface Animal {
    void makeSound();

    default void eat() {
        System.out.println("Yamm");
    }
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Miaww");
    }
}


public class Interfaces {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.makeSound();
        cat.makeSound();
        cat.eat();
    }
}