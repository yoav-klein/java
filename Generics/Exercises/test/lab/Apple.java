
class Apple extends Fruit {
    Apple(int price) {
        super(price);
    }

    @Override
    public int compareTo(Fruit other) {
        return -1;
    }
}