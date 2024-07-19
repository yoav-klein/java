
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql:localhost:3306", "yoav", "yoav")) {
            System.out.println("isValid: " + conn.isValid(0));
            
        }
    }
}