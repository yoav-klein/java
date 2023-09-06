import java.nio.file.*;

class Directories {
    static public void main(String[] args) {
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name: dirs) {
            System.err.println(name);
        }
    }

}