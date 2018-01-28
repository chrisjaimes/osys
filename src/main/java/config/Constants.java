package config;

import javafx.util.Pair;

public abstract class Constants {

	//db codes
	
		public static final String DATABASE = "osysdb";
		
		public static final int INVALID_CREDENTIALS = 1;
		public static final int ADMIN_CONNECTED_TO_DB = 2;
		public static final int CONNECTION_TO_DB_FAILED = 3;
		
		public static final int SUCCESSFUL_DB_OPERATION = 100;
		public static final int DB_ERROR = -100;
		public static final int DB_INSERT_OPERATION_INTERRUPTED = -102;
		public static final int DB_DELETE_OPERATION_INTERRUPTED = -103;
	
	//internal codes
	
		public static final String INVALID_CREDENTIALS_MSG = "Invalid credentials";
		public static final String ADMIN_CONNECTED_TO_DB_MSG = "Connected to " + Constants.DATABASE;
		public static final String CONNECTION_TO_DB_FAILED_MSG = "Connection to dabase failed";
		
		public static final int INTERNAL_ERROR = 0;

}