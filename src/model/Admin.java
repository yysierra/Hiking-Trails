package model;

public abstract class Admin {
	private final static String VALID_USERNAME = "Admin";
	private final static String VALID_PASSWORD = "SCCC";
	
	public static String getValidUsername() {
		return VALID_USERNAME;
	}
	public static String getValidPassword() {
		return VALID_PASSWORD;
	}
	
}
