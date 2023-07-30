
/**
 * This example shows the real power of bounded type parameters in generic methods
 * 
 * We want a compare method that will take an array and a compare object. The array and the compare 
 * object must implement a generic inteface Comparable<T>
 */

interface Comparable<T> {
    public int compareTo(T other);
}

class Person implements Comparable<Person> {
    private int height;

    public Person(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public int compareTo(Person other) {
        if(other.getHeight() > this.height) {
            return 1;
        }
        return 0;
    }
}


class Algo {
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }

    public static void main(String[] args) {
        Person[] people = { new Person(1), 
                            new Person(4), 
                            new Person(5), 
                            new Person(10),
                            new Person(12),
                            };
        Person compare = new Person(7);

        int number = Algo.<Person>countGreaterThan(people, compare);
        System.out.println(number);
    }
}