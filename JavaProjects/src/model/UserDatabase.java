package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UserDatabase<T extends User> {
	HashMap<String, T> userDB;

//CONTRUCTOR
	public UserDatabase() {
		userDB = new HashMap<String, T>();
	}

//GETTER
	public User getUser(String userName) {
		return userDB.get(userName);
	}

	public String getKeys() {
		return userDB.keySet().toString();
	}

//MUTATOR
	public boolean addUser(String userName, T user) {
		if (isUserNameAvailable(userName)) {
			userDB.put(userName, user);
		} else {
			System.out.printf("User name [%s] already taken!\n", userName);
			return false;
		}
		return true;
	}

	public ArrayList<String> getUserDataBase() {

		ArrayList<String> usdb = new ArrayList<String>();
		int userCount = 0;
		int reservationCount = 0;
		for (Entry<String, T> entry : this.userDB.entrySet()) {

			T value = entry.getValue();
			usdb.add("\nuser_" + userCount + "=" + value.getUserName() + "\n" + "password_" + userCount + "="
					+ value.getPassword() + "\n" + "reservations_" + reservationCount + "=" + value.getReservations()
					+ "\n");
			userCount++;
			reservationCount++;
		}
		return usdb;
	}

	public boolean isUserNameAvailable(String userName) {
		if (userDB.containsKey(userName)) {
			return false;
		}
		return true;
	}

	public boolean isUserNameAvailable(T user) {
		return this.isUserNameAvailable(user.getUserName());
	}

	public String toString() {
		return getUserDataBase().toString();
	}

	/**
	 * what do i want out of this class? -get the User info -get database key -get
	 * all information from database -then write to a .txt file
	 */

	public static void main(String args[]) {
		UserDatabase<User> usdb = new UserDatabase();
		for (int i = 0; i < 5; i++) {
			String key = String.valueOf(i);
			usdb.addUser(key, new User(key));
		}
		usdb.addUser("1", new User("1"));
		System.out.println(usdb.getUserDataBase());

	}
}
