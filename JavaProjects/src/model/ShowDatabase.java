package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author belik LINKED HASHMAP VS HASHMAP LINKEDHASHMAP maintains insertion
 *         order of keys,values HASHMAP does not maintain insertion order of
 *         keys,values;
 * @param <T>
 * 
 *            WE NEED TO ADD MORE FUNCTIONALITIES TO THIS LATER
 */
public class ShowDatabase<T extends SeatDatabase<Seat>> {
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
			showdb.add(value.toString() + "\n");
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

	}
}
