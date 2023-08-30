import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;


public class CopyHebrewChars {
    static public void main(String[] args) throws IOException {

        OutputStreamWriter out = null;
        InputStreamReader in = null;

        try {
            out = new OutputStreamWriter(new FileOutputStream("little-johnathan.txt"));
            in = new InputStreamReader(new FileInputStream("hebrew.txt"));

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

        }
        catch(IOException e) {
            System.out.println("IOException: " + e);
        }
        finally {
            out.close();
            in.close();
        }
    }
}