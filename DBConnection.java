package train_ticket_bookig;

import java.sql.*;

public class DBConnection {
	 private static String url="jdbc:mysql://localhost:3306/train";
	 private static String username="root";
	 private static String password="Jey1137";
	 
	 public static Connection getconnection() throws SQLException {
		 return DriverManager.getConnection(url,username,password); 
	 }
}
