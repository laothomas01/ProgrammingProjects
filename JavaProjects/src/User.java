
public class User {
	private String userName;
	private String password;

	User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	User(String userName) {
		this(userName, "");
	}

	User() {
		this("", "");
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setID(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return String.format("user=%s\npassword=%s\n", this.userName, this.password);
	}

}
