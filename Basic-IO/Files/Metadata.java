
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;

class Metadata {
    static public void main(String[] args) {
        Path file = Paths.get("file.txt");
        
        try {
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

            System.out.println("isDirectory: " + attr.isDirectory());
            System.out.println("isOther: " + attr.isOther());
            System.out.println("isRegularFile: " + attr.isRegularFile());
            System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
            System.out.println("size: " + attr.size());
        } catch(IOException e) {
            System.err.println(e);
        }

        try {
            DosFileAttributes attr =
                Files.readAttributes(file, DosFileAttributes.class);
            System.out.println("isReadOnly is " + attr.isReadOnly());
            System.out.println("isHidden is " + attr.isHidden());
            System.out.println("isArchive is " + attr.isArchive());
            System.out.println("isSystem is " + attr.isSystem());
        } catch (UnsupportedOperationException x) {
            System.err.println("DOS file" +
                " attributes not supported:" + x);
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}