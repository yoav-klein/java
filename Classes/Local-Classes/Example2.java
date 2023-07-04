
class StatusKeeper {
    
    int status = 0;

    StatusKeeper(int status) {
        this.status = status;
    }

    void changeStatus(int status) {
        this.status = status;
    }

    void printStatus() {
        class StatusPrinter {
            int status = 111;

            void printStatus() {
                System.out.println(StatusKeeper.this.status);
            }
        }

        StatusPrinter sp = new StatusPrinter();
        sp.printStatus();
    }

    public static void main(String[] args) {
        StatusKeeper sk1 = new StatusKeeper(2);
        StatusKeeper sk2 = new StatusKeeper(5);

        sk1.printStatus();
        sk2.printStatus();
        
    }
}