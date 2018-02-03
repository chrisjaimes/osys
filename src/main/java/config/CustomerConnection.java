package config;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class CustomerConnection implements EnvironmentAware {
	
	private static Environment env;

	public Connection connection;
	
	public CustomerConnection() {}
	
	@Override
    public void setEnvironment(final Environment environment) {
        this.env = environment;
    }
	
	@Bean
	public int connect() {
		if(env.getProperty("database.endpoint") == null || env.getProperty("database.endpoint").isEmpty() 
				|| env.getProperty("database.username") == null || env.getProperty("database.username").isEmpty() 
				|| env.getProperty("database.password") == null || env.getProperty("database.password").isEmpty()) {
			try {
				connection.close(); 
				connection = null;
			} catch(Exception e) {
				;
			}
			
			return Constants.INVALID_CREDENTIALS;
		}
		
		System.out.println("database: " + env.getProperty("database.endpoint"));
		String url = env.getProperty("database.endpoint") + Constants.DATABASE + "?user=" + env.getProperty("database.username") + "&password=" 
				+ env.getProperty("database.password");
		
		try {
			connection = DriverManager.getConnection(url);
			return Constants.ADMIN_CONNECTED_TO_DB;
			
		} catch(Exception e) {
			connection = null;
			return Constants.CONNECTION_TO_DB_FAILED;
		}
	}
	
	public boolean closeConnection() {
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
