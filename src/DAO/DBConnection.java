package bg.ittalents.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton DB connection for all DAO db objects.
 * 
 * @author Niki
 *
 */
public class DBConnection {
	
	private static final String DB_PASSWORD = "ittstudent-123";
	private static final String DB_USER = "ittstudent";
	private static final String DATABASE = "season5_java1";
	private static final String DB_PORT = "3306"; 
	private static final String DB_HOST = "192.168.8.22";
	private static final String DB_URL = DB_HOST + ":" + DB_PORT;

	private static DBConnection connectionInstance = null;
	private static Connection connection = null;


	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection
					("jdbc:mysql://" + DB_URL + "/" + DATABASE, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public static DBConnection getInstance() {
		synchronized (DBConnection.class) {
			if (connectionInstance == null) {
				connectionInstance = new DBConnection();
			}
		}

		return connectionInstance;
	}

}
