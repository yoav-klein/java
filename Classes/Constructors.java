
/**
 * This demonstrates the use of constructors.
 * Constructors are like methods, only their name is the name of the class
 * and no return type
 * 
 * You can have multiple constructors - constructor overloading
 */

class Bicycle {
    int speed;
    int cadence;
    int gear;

    Bicycle() {
        this(0);
    }

    Bicycle(int initialSpeed) {
        this(initialSpeed, 0);
    }

    Bicycle(int initialSpeed, int initialCadence) {
        this(initialSpeed, initialCadence, 0);
    }

    Bicycle(int initialSpeed, int initialCadence, int initialGear) {
        this.speed = initialSpeed;
        this.cadence = initialCadence;
        this.gear = initialGear;
    }

    int getSpeed() {
        return speed;
    }

    int getCadence() {
        return cadence;
    }

    int getGear() {
        return gear;
    }

    static public void main(String[] args) {
        Bicycle myBike = new Bicycle(10);
        System.out.println(myBike.getSpeed());
    }
}