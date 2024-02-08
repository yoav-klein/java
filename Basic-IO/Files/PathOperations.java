
import java.nio.file.*;
import java.io.IOException;

class PathOperations {
    static public void main(String[] args) throws IOException {
        // Microsoft Windows syntax
        Path path = Paths.get("C:\\home\\joe\\foo");

        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());

        
        // relative path
        Path file = Paths.get("non-existing-file.txt");

        // the file doesn't have to exist
        Path abso = file.toAbsolutePath();
        System.out.println(abso);

        // Real path
        file = Paths.get("file.txt");
        // the file have to exist
        Path realPath = file.toRealPath();

        System.out.println(realPath);


        // joining paths
        
        // Microsoft Windows
        Path p1 = Paths.get("C:\\home\\joe\\foo");
        // Result is C:\home\joe\foo\bar
        System.out.format("%s%n", p1.resolve("bar"));

        // Creating a path between two locations
        Path joe = Paths.get("/home/joe");
        Path sally = Paths.get("/home/kuku/bobby/sally");

        System.out.println(sally.relativize(joe));

    }
}