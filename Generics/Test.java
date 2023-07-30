
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

class Test {
    public static void main(String[] args) {
        Person tall = new Person(10);
        Person small = new Person(5);

        System.out.println(small.compareTo(tall));
    }
}