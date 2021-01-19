package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class User {
	private String userName;
	private String password;
	// datetime , list of reserved seats
	HashMap<String, ArrayList<String>> reservations;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.reservations = new HashMap<String, ArrayList<String>>();
	}

	public User(String userName) {
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

	// each user will have their set of reserved seats
	public void addReservations(String dateTime, String seatID) {
		// the arraylist returns a specific seat for the given datetime
		ArrayList<String> seats = this.reservations.get(dateTime);
		// if arraylist is empty
		if (seats == null) {
			seats = new ArrayList<String>();
			seats.add(seatID);
			this.reservations.put(dateTime, seats);
//			System.out.println(reservations.toString());
		} else {
			seats.add(seatID);
//			System.out.println(reservations.toString());

		}

	}

	// each user will have their set of reserved seats
	public void addReservations(String Date, String Time, String seatID) {
		String dateTime = Date.trim() + "|" + Time.trim();
		// the arraylist returns a specific seat for the given datetime
		ArrayList<String> seats = this.reservations.get(dateTime);
		// if arraylist is empty
		if (seats == null) {
			seats = new ArrayList<String>();
			seats.add(seatID);
			this.reservations.put(dateTime, seats);

		} else
			seats.add(seatID);

	}

	public boolean addReservations(Seat s) {
//		ArrayList<String> seats = this.reservations.get(":" + s.getSeatDateTime() + ":");
		ArrayList<String> seats = this.reservations.get( s.getSeatDateTime());
		if (s.isOccupied() == true) {
			System.out.println("Seat " + "[" + s.getSeatID() + "]" + " is not available!");
			return false;
		}
		if (seats == null) {
			seats = new ArrayList<String>();
			seats.add(s.getSeatID());
//			this.reservations.put(":" + s.getSeatDateTime() + ":", seats);
			this.reservations.put(s.getSeatDateTime(), seats);
			return true;
		} else {
			seats.add(s.getSeatID());
			return true;
		}

	}

	public HashMap<String, ArrayList<String>> getReservations() {
		return this.reservations;
	}

	public ArrayList<String> getReservation(String dateTime) {
//		return this.reservations.get(":" + dateTime + ":");
		return this.reservations.get(dateTime);
	}

	public ArrayList<String> getReservation(Seat s) {
		return this.reservations.get(s.getSeatDateTime());
	}

	public void setReservations(HashMap<String, ArrayList<String>> reservations) {
		this.reservations = reservations;
	}

	public boolean removeSeatReservation(String dateTime, String seatID) {
		ArrayList<String> seats = this.reservations.get(dateTime);
		if (seats != null) {
			seats.remove(seatID);
			return true;
		}
		return false;
	}

	public boolean removeSeatReservation(Seat s) {
		ArrayList<String> seats = this.reservations.get(s.getSeatDateTime());
		if (seats != null) {
			seats.remove(s.getSeatID());
			return true;
		}
		return false;
	}

	public String viewAllReservations(String dateTime) {
		return dateTime + reservations.get(dateTime).toString();
	}

	public String toString() {

		return String.format("user=%s\npassword=%s\n", this.userName, this.password) + "reservations="
				+ this.reservations + "\n";
	}

	public String displayUser() {
		return String.format("user=%s\npassword=%s\n", this.userName, this.password);
	}

}
