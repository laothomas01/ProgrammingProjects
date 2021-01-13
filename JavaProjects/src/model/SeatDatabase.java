package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

//this is technically a seat data base
//the showdatabase will handle the seat availability.
//this is to keep track of all the seats for the show
//a show has seats. might as well call this seat instead of showdatabase
public class SeatDatabase<T extends Seat> {
	private String date;
	private String time;

	// key: can be the show's time or date. value: takes a seat Object.
	// let's choose using the time as a key or we can just concatenate the date and
	// time as strings
	HashMap<String, T> seatDB;

	public SeatDatabase() {
		this(" ", " ");
	}

	public SeatDatabase(String date, String time) {
		this.date = date;
		this.time = time;
		seatDB = new HashMap<String, T>();
	}

	// seatID: is String seatSection + Integer.toString(seatNumber);
	// example: m1,m2,m3
	public Seat getSeatID(String seatID) {
		return seatDB.get(seatID);
	}

	public String getSeatID(T seat) {
		return seat.getSeatSection() + Integer.toString(seat.getSeatNumber());
	}

	public String getDate() {
		return this.date;
	}

	public String getTime() {
		return this.time;
	}

	public String getDateTime() {
		return this.date + "|" + this.time;
	}

	public void addSeat(String seatID, T seat) {

		seatDB.put(seatID, seat);

	}
	

	public void addSeat(T seat) {
		seatDB.put(seat.getSeatSection() + Integer.toString(seat.getSeatNumber()), seat);
	}

	public ArrayList<String> getSeatDataBase() {
		ArrayList<String> sdb = new ArrayList<String>();
		for (Entry<String, T> entry : this.seatDB.entrySet()) {
			T value = entry.getValue();
			String seatToAdd = value.getSeatSection() + value.getSeatNumber() + "|" + "$" + value.getSeatPrice() + "|"
					+ value.getSeatDate() + "|" + value.getSeatTime() + "\n";

			sdb.add(seatToAdd);
		}
		return sdb;
	}

	public String toString() {
		return seatDB.toString();
	}

}
