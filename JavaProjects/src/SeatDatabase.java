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

	public String getDateTime() {
		return this.date + "@" + this.time;
	}

	public void addSeat(String seatID, T seat) {
//		if (isSeatOccupied(seat)) {
//			System.out.println("This seat:" + seat + "not available!");
//			return false;
//		} else {
//			System.out.println("Seat is available!");
		seatDB.put(seatID, seat);
//			System.out.println("Seat added!");
//		return true;
	}

	public void addSeat(T seat) {
		seatDB.put(seat.getSeatSection() + Integer.toString(seat.getSeatNumber()), seat);
	}

//	public boolean isSeatOccupied(String seatID, T seat) {
//		if(seat.isOccupied())
//		{
//			
//		}
//
//	}

	public ArrayList<String> getSeatDataBase() {
		ArrayList<String> sdb = new ArrayList<String>();
		for (Entry<String, T> entry : this.seatDB.entrySet()) {
			T value = entry.getValue();
			sdb.add(Double.toString(value.getSeatPrice()));

		}
		return sdb;
	}

	public String toString() {
		return seatDB.toString();
	}

	public static void main(String args[]) {
		SeatDatabase<Seat> sdb = new SeatDatabase<Seat>();
		Seat seat1 = new Seat(35.0, 1, false, "m", "2020-12-23", "06:30PM");
		sdb.addSeat(seat1);
		Seat seat2 = new Seat(35.0, 1, false, "n", "2020-12-23", "06:30PM");
		sdb.addSeat(seat2);
		Seat seat3 = new Seat(35.0, 1, false, "m", "2020-12-23", "06:30PM");
		sdb.addSeat(seat3);
		System.out.println(sdb.getSeatDataBase());

//
//		Seat seat3 = new Seat(35.0, 1, false, "m", "2020-12-23", "06:30PM");
//		sdb.addSeat(seat3);

		// BUG HERE: cannot add seat for another date because of key duplicates.
		// Solution: Since we cannot have multiple m1's for different dates, we need to
		// variant the keys
		// 0_m1,1_m1,2_m2,3_m3[...]n
//		System.out.println(sdb.getSeatID("m1"));

	}

}
