
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.nio.file.FileAlreadyExistsException;
import java.io.IOException;

class FileOperations {
    static public void main(String[] args) {
        Path file = Paths.get("file.txt");
        Path noFile = Paths.get("non-existing-file.txt");

        // checking if a file exists
        System.out.println(Files.exists(file));
        System.out.println(Files.notExists(noFile));

        // checking if a file is a regular file and if executable
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
        } catch(FileAlreadyExistsException e) {
            System.out.println("File already exists !");  
        } catch(IOException e) {
            System.err.println(e);
        }

        // the copy fails if the file exists:
        try {
            Files.copy(file, copyDest);
        } catch(FileAlreadyExistsException e) {
            System.out.println("File already exists !");  
        } catch(IOException e) {
            System.out.println(e);
        }

        // unless the REPLACE_EXISTING is specified
        try {
            Files.copy(file, copyDest, StandardCopyOption.REPLACE_EXISTING);
        } catch(FileAlreadyExistsException e) {
            System.out.println("File already exists !");  
        } catch(IOException e) {
            System.out.println(e);
        }

        // move a file

        Path moveDest = Paths.get("moved.txt");
        try {
            Files.move(copyDest, moveDest);
        } catch(FileAlreadyExistsException e) {
            System.out.println("File already exists !");
        } catch(IOException e) {
            System.out.println("Some error" + e);
        }

    }
}