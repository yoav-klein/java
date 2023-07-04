
/**
 * shown capabilities:
 *  - class declaration
 *  - instance variables (non-static fields)
 *  - class varialbes (static fields)
 *  - using the this keyword to invoke constructor
 *  - private fields
 */

class Bicycle {
    private int gear = 0;
    private int cadence = 0;
    private int speed = 0;
    
    private int serial;

    static int count = 0;

    Bicycle() {
        this(0);
    }

    Bicycle(int gear) {
        this(gear, 0);
    }

    Bicycle(int gear, int cadence) {
        this(gear, cadence, 0);
    }

    Bicycle(int gear, int cadence, int speed) {
        serial = count++;
        
        this.gear = gear;
        this.cadence = cadence;
        this.speed = speed;
    }

    void accelerate(int value) {
        speed += value;
    }

    void applyBreak(int value) {
        speed -= value;
    }

    static int getCount() {
        return count;
    }

    void printBicycle() {
        System.out.println("Bicycle number: " + serial + ", Speed: " + speed + ", Cadence: " + cadence + " Gear: " + gear);
    }

    public static void main(String[] args) {  
        Bicycle b1 = new Bicycle();
        Bicycle b2 = new Bicycle(1, 2);

        b1.printBicycle();
        b2.printBicycle();

        b1.accelerate(15);
        b1.printBicycle();

        // You can access a class variable (static field) using the class name,
        // without any object:
        System.out.println("Current count: " + Bicycle.count);
    }
}