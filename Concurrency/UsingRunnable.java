
class HelloRunnable implements Runnable {
    public void run() {
        System.out.println("Hello");
    }
}


class Simple {
    public static void main(String[] args) {
        new Thread(new HelloRunnable()).start();
    }
}