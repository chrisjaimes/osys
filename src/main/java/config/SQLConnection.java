package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.AbstractMap;
import java.util.Optional;
import javafx.util.Pair;

public class SQLConnection {

	public static Connection connection;
	
	public static int connect(AbstractMap.SimpleEntry<String, String> credentials, String host, String port) {
		if(credentials == null || credentials.getKey() == null || credentials.getValue() == null) {
			try {
				connection.close(); 
				connection = null;
			} catch(Exception e) {
				;
			}
			
			return Constants.INVALID_CREDENTIALS;
		}
		
		String url = "jdbc:postgresql://" + host +
				":" + port + "/" +
				Constants.DATABASE + "?user=" + credentials.getKey() +
				"&password=" + credentials.getValue();
		
		try {
			connection = DriverManager.getConnection(url);
			return Constants.ADMIN_CONNECTED_TO_DB;
			
		} catch(Exception e) {
			connection = null;
			return Constants.CONNECTION_TO_DB_FAILED;
		}
	}
	
	public static boolean closeConnection() {
		try {
			if(connection == null)
				return true;
			
			if(!connection.isClosed())
				connection.close();
			
			connection = null;
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
}
