
import java.nio.file.*;
import java.io.IOException;
import java.nio.charset.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

class ReadWriteFiles {
    static public void main(String[] args) {
        try {

            // readAllBytes
            Path file = Paths.get("file.txt");
            byte[] fileArray;
            fileArray = Files.readAllBytes(file);

            for(Byte b: fileArray)  System.out.format("%c", b);

            // write
            Path newFile = Paths.get("poem.txt");
            Files.write(newFile, fileArray);

            // buffered reading
            Charset charset = Charset.forName("US-ASCII");
            try (BufferedReader reader = Files.newBufferedReader(newFile, charset)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

            // buffered writing
            Path buffWrite = Paths.get("buffered.txt");
            String s = "Well, I'm sick of it..";
            try (BufferedWriter writer = Files.newBufferedWriter(buffWrite, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }  

            // reading a file by using stream I/O
            
            try (InputStream in = Files.newInputStream(newFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException x) {
                System.err.println(x);
            }

            try {
                Path tempFile = Files.createTempFile(null, ".myapp");
                System.out.format("The temporary file" +
                    " has been created: %s%n", tempFile);
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

            
        }
        catch(IOException e) {
            System.err.println(e);
        }
    }
}