
class Test {
    public static void main(String[] args) {
        Cat c = new Cat();
        Dog d = new Dog();

        Dog d1 = new Dog(5); // special dog with 5 legs
        c.makeSound();
        d.makeSound();
        c.breathe();
        d.breathe();
        c.reproduce();
        d.reproduce();
        System.out.println(d1.getNumLegs());
    }
}