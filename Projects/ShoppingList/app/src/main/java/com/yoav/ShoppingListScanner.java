
import java.util.Scanner;
import java.util.InputMismatchException;

import java.lang.RuntimeException;

class ShoppingListScanner {
    private Scanner s = new Scanner(System.in);

    int nextInt(int start, int end) {
        int read = 0;
        try {
            read = s.nextInt();
        } catch(InputMismatchException e) {
            s.next();
            throw new RuntimeException("Invalid input");
        }

        if((read > end) || (read < start)) {
            throw new RuntimeException("Out of range");
        }

        return read;
    }

    String next() {
        return s.next();
    }
}