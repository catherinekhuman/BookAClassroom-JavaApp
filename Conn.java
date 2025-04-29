import java.sql.*;

//Class to handle the database connection
public class Conn {
	
	// Declare Connection and Statement objects for database operations
	Connection c; // Connection object to establish connection with the database
	Statement s;  // Statement object to execute SQL queries
	
	// Constructor to initialize the database connection
	public Conn() {
		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/* Establish a connection to the database
            "jdbc:mysql:///MiniProject" specifies the database URL (MiniProject is the database name)
            "root" is the database username, and "CatMySQL@123" is the password */
			c = DriverManager.getConnection("jdbc:mysql:///MiniProject", "root", "CatMySQL@123");
			
            // Create a Statement object to execute SQL queries
			s = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
