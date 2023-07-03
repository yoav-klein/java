
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    public static void main(String[] args) {
        Dog d = new Dog();

        d.makeSound();
        d.eat();

    }
}