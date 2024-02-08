
import java.nio.file.*;
import java.io.IOException;
import java.nio.charset.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;

import static java.nio.file.StandardOpenOption.*;

class ReadWriteFiles {
    static public void main(String[] args) {
        try {
            
            // readAllBytes
            System.out.println("--- readAllBytes ---");
            Path file = Paths.get("file.txt");
            byte[] fileArray;
            fileArray = Files.readAllBytes(file);

            for(Byte b: fileArray)  System.out.format("%c", b);
            System.out.println();

            // write
            System.out.println("--- write ---");
            Path newFile = Paths.get("poem.txt");
            Files.write(newFile, fileArray);

            // buffered reading
            // reading from the newly created file
            System.out.println("--- buffered reader ---");
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
            // writing to buffered-writing.txt
            System.out.println("--- Buffered writing");
            Path buffWrite = Paths.get("buffered-writing.txt");
            String s = "Well, I'm sick of it..";
            try (BufferedWriter writer = Files.newBufferedWriter(buffWrite, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }  

            // reading a file by using stream I/O
            // reading the buffered-writing.txt file
            System.out.println("--- Reading a file using Stream I/O");
            try (InputStream in = Files.newInputStream(buffWrite);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException x) {
                System.err.println(x);
            }

            // writing a file using  I/O streams
            System.out.println("--- Writing to a file using I/O Stream");
            String s1 = "INFO:: Everything is great\n";
            byte data[] = s1.getBytes();
            Path p = Paths.get("./logfile.txt");

            try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
                out.write(data, 0, data.length);
            } catch (IOException x) {
                System.err.println(x);
            }

            // Temporary file
            System.out.println("Temporary file");
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