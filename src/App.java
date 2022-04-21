import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        //connect to mysql database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/arun?allowPublicKeyRetrieval=true&useSSL=false", "aruntest", "Test@123");
        //create statement
        Statement stmt = conn.createStatement();
        //execute query
        ResultSet rs = stmt.executeQuery("select * from class");
        //process result set
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2)+ " " + rs.getString(3)+ " " + rs.getString(4));
        }
        //close connection
        conn.close();
        
    }
}
