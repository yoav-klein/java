
/**
 * Demostrates the capabilities of local class - what it can access, etc.
 */

class StatusKeeper {
    
    int status = 0;
    static int staticStatus = 20;

    StatusKeeper(int status) {
        this.status = status;
    }

    void changeStatus(int status) {
        this.status = status;
    }

    /*void isLocalClassVisibleHere(StatusPrinter sp) { // local classes are only visible in the scope they're declared
        System.out.println("Status printer: " + sp);
    }*/
    static void staticPrintStatus() {
        // you can call this class also StatusPrinter - no problem
        class StatusPrinter {
            void printStatus() {
                System.out.println(staticStatus);
                // System.out.println(status); // error - can't access non-static field from static method
            }

            /*static void foo() { // no static methods in local class
                System.out.println("foo");
            }*/
        }

        StatusPrinter sp = new StatusPrinter();
        sp.printStatus();
    }

    void printStatus() {
        class StatusPrinter {
            int status = 111;
            static final int constant = 120; // static fields in local classes are allowed only if they're final

            void printStatus() {
                // shadowing - in order to print the "status" field of the StatusKeeper class
                // you must refer to it explicitly since the field in StatusPrinter shadows it
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

        sk1.staticPrintStatus();

        
        
    }
}