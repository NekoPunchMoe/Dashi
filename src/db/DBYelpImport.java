package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create DB tables in MySQL.
 *
 */
public class DBYelpImport {

    public static void main(String[] args) {
   	 try {
                        // Ensure the driver is imported.
   		 Class.forName("com.mysql.jdbc.Driver").newInstance();
   		 Connection conn = null;

   		 try {
   			 System.out.println("Connecting to \n" + DBUtil.URL);
   			 conn = DriverManager.getConnection(DBUtil.URL);
   		 } catch (SQLException e) {
   			 System.out.println("SQLException " + e.getMessage());
   			 System.out.println("SQLState " + e.getSQLState());
   			 System.out.println("VendorError " + e.getErrorCode());
   		 }
   		 if (conn == null) {
   			 return;
   		 }
   		 String query = "Select business_id From history Where user_id = ?";
   		 String userId = "1111";
   		 PreparedStatement statement = conn.prepareStatement(query);
   		 statement.setString(1, userId);
   		 ResultSet rSet = statement.executeQuery();
   		 rSet.next();
   		 System.out.println(rSet.getString(1));
   	 } catch (Exception e) {
   		 System.out.println(e.getMessage());
   	 }
    }
}