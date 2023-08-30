
import java.nio.charset.Charset;

public class DefaultCharset {
    static public void main(String[] args) {
        System.out.println(Charset.defaultCharset().displayName());
    }
}