
public abstract class Animal implements SoundMaking, Organism {
    int numLegs = 4; // abstract classes can have non-final non-static fields

    // abstract class can implement methods
    public void breathe() {
        System.out.println("Haa, haa");
    }
    // it can leave implementation of abstract methods inherited from interfaces
    // to be implemented by sub-classes, so we don't implement makeSound here.

    // abstract methods must be implemented by sub-classes
    abstract void reproduce();

    Animal() {}
    Animal(int numLegs) {
        this.numLegs = numLegs;
    }

    int getNumLegs() {
        return numLegs;
    }
    
}