public class Fruit implements Comparable<Fruit> {
    private int price;

    Fruit(int price) {
        this.price = price;
    }

    public int compareTo(Fruit other) {
        if(other.price > this.price) {
            return 1;
        } else if (other.price < this.price) {
            return -1;
        } else {
            return 0;
        }
    }
}