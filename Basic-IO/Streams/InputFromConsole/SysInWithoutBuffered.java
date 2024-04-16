
/**
 * 
 * using System.in object
 * 
 * if you don't wrap the InputStreamReader with a BufferedReader, you can't use line-based I/O.
 * 
 */

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class SysInWithoutBuffered {
    public static void main(String[] args) throws IOException {
        InputStreamReader cin = new InputStreamReader(System.in);
        //BufferedReader reader = new BufferedReader(cin);

        while(true) {
            char c = (char)cin.read();
            // the String returned by readLine doesn't contain the line termination characters

            System.out.println(c);
        }
    }
}
