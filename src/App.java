import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


// create table arun(username varchar(20) , password varchar(20) ) values("arun" , "arun@123");
public class App {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }





    public static void main(String[] args) throws Exception {
        //connect to mysql database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/arun?allowPublicKeyRetrieval=true&useSSL=false", "aruntest", "Arun@123");
        //create statement
        Statement stmt = conn.createStatement();
        System.out.println("1 to create user , 3 for sign in ");
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        System.out.println(value);
        if(value == 1){
            System.out.println("enter username");
            String username =  scanner.next();
            System.out.println("enter password");
            String password =  scanner.next();
            String hashed_password = toHexString(getSHA(password));
            // System.out.println(toHexString(getSHA(password)));
            try {
                ResultSet rs = stmt.executeQuery("select * from arun where username = '"+username+"';");
                if(rs.next()){
                    System.out.println("user already exists");
                }
                else{
                    stmt.executeUpdate("insert into arun(username, password) values('"+username+"','"+hashed_password+"');");
                    System.out.println("user created");
                }
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println(e.getMessage());
            }
        

        }else if(value == 3){

            System.out.println("enter username");
            String username =  scanner.next();
            System.out.println("enter password");
            String password =  scanner.next();
            String hashed_password1 = toHexString(getSHA(password));
            // System.out.println(hashed_password1);
            ResultSet rs = stmt.executeQuery("select * from arun where username = '"+username+"';");
            
            if(rs.next()){
                if(rs.getString(2).equals(hashed_password1) && rs.getString(1).equals("admin")){
                    System.out.println("Hey admin login success ,you now have the shell");
                    while (true) {
                       while (true) {
                        System.out.println("1- for get all users, 2-for delete users");
                        String adminselect = scanner.next();
                        switch (adminselect) {
                            case "1":
                                getallusers(stmt);
                                break;
                            case "2":
                                deleteusers(stmt);
                                break;
                            default:
                                break;
                        }
                        if(adminselect.equals("3")){
                            break;
                        }
                        
                       }
                    }
                    
                    // System.out.println(rs.getString(2));
                    // System.out.println(hashed_password1);
                }
                else if(rs.getString(2).equals(hashed_password1) ){
                    System.out.println("heyy user login success");
                }
                else{
                    System.out.println("login failed");
                    // System.out.println(rs.getString(2));
                    // System.out.println(hashed_password1);

                    // System.out.println(hashed_password1.length());


                }
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
    private static void deleteusers(Statement stmt) {
        while(true){
            getallusers(stmt);
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter username to delete, 3- to back");
        String username1 =  scanner.next();
        if(username1.equals("3")){
            break;
        }
        try {
            stmt.executeUpdate("delete from arun where username = '"+username1+"';");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("user deleted");
        }
    }
    private static void getallusers( Statement stmt) {
        try {
            ResultSet rs  = stmt.executeQuery("select * from arun");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


// Java program to calculate SHA hash value


}



