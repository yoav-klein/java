
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.io.IOException;

class FileOperations {
    static public void main(String[] args) {
        Path file = Paths.get("file.txt");
        Path noFile = Paths.get("non-existing-file.txt");

        System.out.println(Files.exists(file));
        System.out.println(Files.notExists(noFile));

        boolean isRegularExecutableFile = Files.isRegularFile(file) &
        Files.isReadable(file) & Files.isExecutable(file);

        System.out.println(isRegularExecutableFile);

        // deleting a file using delete
        try {
            Files.delete(noFile);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", noFile);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", noFile);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }

        // deleting a file using deleteIfExists throws no NoSuchFileException
        try {
            Files.deleteIfExists(noFile);
        } catch(IOException e) {
            System.err.println(e);
        }

        // copying a file
        Path copyDest = Paths.get("copy.txt");
        try {
            Files.copy(file, copyDest);
        }
        catch(IOException e) {
            System.err.println(e);
        }

    }
}