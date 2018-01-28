package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.AbstractMap;
import java.util.Optional;
import javafx.util.Pair;

public class SQLConnection {

	public static Connection connection;
	
	public static String connect(AbstractMap.SimpleEntry<String, String> credentials, String host) {
		if(credentials == null || credentials.getKey() == null || credentials.getValue() == null) {
			try {
				connection.close(); 
				connection = null;
			} catch(Exception e) {
				;
			}
			
			return "Invalid credentials";
		}
		
		String url = "jdbc:postgresql://" + host +
				":" + Constants.PORT + "/" +
				Constants.DATABASE + "?user=" + credentials.getKey() +
				"&password=" + credentials.getValue();
		
		try {
			connection = DriverManager.getConnection(url);
			return 
					"Connected to " + host + ":" + 
					Constants.PORT + "/" + 
					Constants.DATABASE;
			
		} catch(Exception e) {
			connection = null;
			return "Connection to database failed";
		}
	}
	
}
