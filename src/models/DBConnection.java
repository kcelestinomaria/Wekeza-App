package models;

//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/wekeza-neobank";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // public Connection con;

    // Method to establish a connection to the MySQL database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Test the database connection
    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("Connected to MySQL database!");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Failed to connect to MySQL database: " + e.getMessage());
        }
    }
    /*
     * public static Connection getConnection() {
     * try {
     * 
     * Class.forName("com.mysql.jdbc.Driver");
     * 
     * con = DriverManager.getConnection(
     * "jdbc:mysql://localhost:3306/wekeza-app-database", "root", "");
     * 
     * return con;
     * } catch (ClassNotFoundException | SQLException e) {
     * System.out.println("Database connection failed. See error below.");
     * System.out.println(e);
     * }
     * return con;
     * }
     */

}
