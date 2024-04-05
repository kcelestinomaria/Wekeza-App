Great choice! Let's proceed with JDBC for database connectivity. Here's how we can establish a connection to the MySQL database from our Java application using JDBC:

1. **Download MySQL Connector/J**: Download the MySQL Connector/J library from the MySQL website and add it to your project's classpath.

2. **Write Java Code for Database Connection**: Write Java code to establish a connection to the MySQL database using JDBC.

Here's a basic example of how to establish a JDBC connection in Java:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/neobank";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    // Method to establish a connection to the MySQL database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
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
}
```

Replace `"your_username"` and `"your_password"` with your MySQL username and password.

Once you have established the database connection successfully, we can proceed with implementing user registration and login functionality. Let me know if you need assistance with that part!