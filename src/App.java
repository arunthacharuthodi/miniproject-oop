import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
// create table arun(username varchar(20) , password varchar(20) ) values("arun" , "arun@123");
public class App {
    public static void main(String[] args) throws Exception {
        //connect to mysql database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/arun?allowPublicKeyRetrieval=true&useSSL=false", "arun", "Arun@123");
        //create statement
        Statement stmt = conn.createStatement();
        System.out.println("1 to create user , 2 for get alla users, 3 for sign in ");
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        System.out.println(value);
        if(value == 1){
            System.out.println("enter username");
            String username =  scanner.next();
            System.out.println("enter password");
            String password =  scanner.next();
            var data = "insert into arun(username, password) values('"+username+"', '"+password+"');";
            ResultSet rs = stmt.executeQuery(data);
            System.out.println(rs);
        }else
            if(value == 2){
                ResultSet rs = stmt.executeQuery("select * from arun;");
                while (rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2));
                    }

        }else if(value == 3){
            // select custid as 'Customer ID' , custname as 'Customer Name',custaddress as 'Address' , custcity as 'City',custpincode as 'Pincode',custemail as 'Email',custcontactno as 'Customer Contact No' from tblcustomer where contains(custaddress,'surat');

            // System.out.println("enter username");
            // String username =  scanner.next();
            // System.out.println("enter password");
            // String password =  scanner.next();
            ResultSet rs = stmt.executeQuery("select user1 as username from arun where contains(user1, 'arun');");
            while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                }
        
        }

        //execute query
        // ResultSet rs = stmt.executeQuery("insert into arun(username, password) values('arun' , 'password');");
        //process result set
        // while (rs.next()) {
        //     System.out.println(rs.getString(1) + " " + rs.getString(2)+ " " + rs.getString(3)+ " " + rs.getString(4));
        // }
        //close connection
        conn.close();
        
    }
}
