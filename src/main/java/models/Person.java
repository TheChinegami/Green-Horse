package models;

public class Person {
	
	private int id;
	private String email;
	private String password;
	private int token;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}	
	
}
