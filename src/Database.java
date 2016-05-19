import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Database {
	private static String Host = "localhost";
	private static String Port = "3306";
	private static String Username = "root";
	private static String Password = "root";
	private static String DB = "twitter";

	static Connection getConnection() {
		com.mysql.jdbc.Connection dbConnection = null;
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	    try {
	        dbConnection = (Connection) DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + DB, Username, Password);
	        return dbConnection;
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return dbConnection;
	}
}
