// Backend Part 2: DBConnection
// VTU Concept: JDBC (Java Database Connectivity) - Step 1 and 2
//
// JDBC works in simple steps:
//   Step 1: Load the driver class (Class.forName)
//   Step 2: Make the connection (DriverManager.getConnection)
//   Step 3: Run SQL (done in Database.java)
//   Step 4: Close the connection
//
// BEFORE USING MYSQL, CHANGE THESE 3 VALUES:
//   - Create the database first by running database.sql in MySQL.
//   - USER and PASSWORD must match your MySQL login.
//   - Put mysql-connector-j.jar in the lib folder (see README).

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/online_exam_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // <-- your MySQL password here

    // Returns a connection, or null if MySQL is not available.
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Step 1: load driver
            return DriverManager.getConnection(URL, USER, PASSWORD); // Step 2
        } catch (Exception e) {
            return null;
        }
    }

    // True if the program can connect to MySQL right now.
    public static boolean isAvailable() {
        Connection con = getConnection();
        if (con == null) {
            return false;
        }
        try {
            con.close();
        } catch (Exception e) {
            // ignore
        }
        return true;
    }
}
