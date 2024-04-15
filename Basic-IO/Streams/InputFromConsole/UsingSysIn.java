
/**
 * 
 * using System.in object
 * 
 * System.in is an input stream with no character stream features
 * To use Standard Input as a character stream, wrap System.in in InputStreamReader.
 * 
 * In order to read whole lines instead of single characters, warp the InputStreamReader with BufferedReader
 * 
 */

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class UsingSysIn {
    public static void main(String[] args) throws IOException {
        InputStreamReader cin = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(cin);

        while(true) {
            String line = reader.readLine(); 
            // the String returned by readLine doesn't contain the line termination characters

            System.out.println(line);
        }
    }
}