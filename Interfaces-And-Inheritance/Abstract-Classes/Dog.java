public class Dog extends Animal {
    Dog() {}
    Dog(int numLegs) {
        super(numLegs);
    }

    public void makeSound() {
        System.out.println("Woof!");
    }

    public void reproduce() {
        System.out.println("reproducing like dogs do");
    }
}