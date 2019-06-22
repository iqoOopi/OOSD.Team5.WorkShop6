/******************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Day 6 Assignment
 * Date: May 16, 2019.
 * Author: Tim Leslie
 * Description: This DBHelper class contains a static method which establishes a connection
 * to the TravelExperts database.
 *******************************************************************************************/
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    // method to connect to the database and return a connection object
    public static Connection getConnection()
    {
        // declare a Connection reference variable and set to null
        Connection conn = null;
        try {
            // load the database driver
            //Class.forName("com.mysql.jdbc.Driver"); // This one is depreciated. It might cause compilation errors
        	Class.forName("com.mysql.cj.jdbc.Driver");

            //instantiate a connection object to the TravelExperts database
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travelexperts",
                    "root",
                    "P@ssw0rd");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return conn;
    }
}
