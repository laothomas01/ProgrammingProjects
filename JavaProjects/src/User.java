
public class User {
	private String ID;
	private String password;

	User(String ID, String password) {
		this.ID = ID;
		this.password = password;
	}

	public String getID() {
		return this.ID;
	}

	public String getPassword() {
		return this.password;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return String.format("User: %s Password: %s\n", this.ID, this.password);
	}
	
	

	
	
}
