
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class Hebrew {
    static public void main(String[] args) throws IOException {
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream("hebrew-out.txt"), "UTF-32");
            String word = "שלום עולם";
            out.write(word, 0, word.length());
        } 
        catch(IOException e) {
            System.out.println("Failed: " + e);
        }
        finally {
            out.close();
        }
    }
}