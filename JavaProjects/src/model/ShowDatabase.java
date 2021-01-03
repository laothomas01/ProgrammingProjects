package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
/**
 * 
 * @author belik
 * LINKED HASHMAP VS HASHMAP
 * LINKEDHASHMAP maintains insertion order of keys,values
 * HASHMAP does not maintain insertion order of keys,values;
 * @param <T>
 * 
 * WE NEED TO ADD MORE FUNCTIONALITIES TO THIS LATER
 */
class ShowDatabase<T extends SeatDatabase<Seat>> {
	private String date;
	private String time;
	HashMap<String, T> showDB;

	public ShowDatabase() {
		showDB = new HashMap<String, T>();
	}

	/*
	 * HashMap<dateTime,SeatDatabase<Seat>>; ex:
	 * HashMap<"2020-12-23@06_30_PM",HashMap<seatID,Seat()>>;
	 * 
	 */
	public SeatDatabase<Seat> getShowID(String date, String time) {
		return showDB.get(date + "@" + time);
	}

	public SeatDatabase getShowID(String showID) {
		return showDB.get(showID);
	}

	public void addShow(String showID, T SeatDatabase) {
		showDB.put(showID, SeatDatabase);
	}

	public Set<String> getKeySet() {
		return showDB.keySet();
	}
//	public void addShow(T SeatDatabase) {
//		showDB.put(getShowID(), SeatDatabase);
//	}

	public void addShow(String date, String time, T SeatDatabase) {
		showDB.put(date + "@" + time, SeatDatabase);
	}

	public ArrayList<String> getShowDatabase() {
		ArrayList<String> showdb = new ArrayList<String>();
		for (Entry<String, T> entry : this.showDB.entrySet()) {
			T value = entry.getValue();
			showdb.add(value.toString());
		}
		return showdb;
	}

	public String toString() {
		return showDB.toString();
	}

	public static void main(String args[]) {
		ShowDatabase<SeatDatabase<Seat>> showdb = new ShowDatabase<>();
		ShowDatabase<SeatDatabase<Seat>> showdb2 = new ShowDatabase<>();
		SeatDatabase<Seat> sdb = new SeatDatabase<Seat>();
		Seat seat1 = new Seat(35.0, 1, false, "m", "2020-12-23", "06:30PM");
		Seat seat2 = new Seat(35.0, 1, false, "m", "2020-12-24", "06:30PM");
		Seat seat3 = new Seat(35.0, 1, false, "m", "2020-12-25", "06:30PM");

		Seat seat4 = new Seat(35.0, 1, false, "m", "2020-12-23", "08:30PM");
		Seat seat5 = new Seat(35.0, 1, false, "m", "2020-12-24", "08:30PM");
		Seat seat6 = new Seat(35.0, 1, false, "m", "2020-12-25", "08:30PM");
		sdb.addSeat("1_" + sdb.getSeatID(seat1), seat1);
		sdb.addSeat("2_" + sdb.getSeatID(seat2), seat2);
		sdb.addSeat("3_" + sdb.getSeatID(seat3), seat3);
		showdb.addShow("2020-12-23", "06:30PM", sdb);
//		sdb.addSeat("4_" + sdb.getSeatID(seat4), seat4);
//		sdb.addSeat("5_" + sdb.getSeatID(seat5), seat5);
//		sdb.addSeat("6_" + sdb.getSeatID(seat6), seat6);
//		showdb.addShow("2020-12-24", "08:30PM", sdb);

		System.out.println(showdb.getShowDatabase());
//		System.out.println();
//
//		System.out.println(showdb.toString());
//
//		System.out.println(showdb.getKeySet());

		System.out.println("INFORMATION RETRIEVAL");
		System.out.println(showdb.getShowID("2020-12-23", "06:30PM"));
//		System.out.println("INFORMATION RETRIEVAL");
//		System.out.println(showdb.getShowID("2020-12-24@08:30PM"));
	}
}
