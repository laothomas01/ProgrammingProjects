package driver;

import java.io.IOException;
import java.util.ArrayList;

import model.Seat;
import model.SeatDatabase;
import model.ShowDatabase;
import model.User;
import model.UserDatabase;
import util.FileConstants;
import util.FileIO;

public class Main implements FileConstants {
	public static void main(String args[]) throws IOException {
		String user_Reservations = reservation_filepath;
		String seatData = seats_filepath;
//		Main.test_UserDatabase();
//		Main.test_SeatDatabase();
//		Main.test_BuildShowDB();
//		Main.test_AddReservations(user_Reservations);
//		Main.test_FILEIO_buildSeatDataBase(seatData);
//		Main.test_RemoveSeatReservations(user_Reservations);
//		Main.viewAllReservations(user_Reservations);
		FileIO io = new FileIO();
//		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(seatData);
//		System.out.println(shwDB.getShowDatabase());
		Main.test_makingReservations();
	}

	public static void test_UserDatabase() {
		System.out.println("##############USER DATABASE TESTING###########");
		UserDatabase<User> usdb = new UserDatabase<User>();
		// create a new user
		User user = new User("Thomas", "Lao");
		User user2 = new User("Thomas", "Lao");
		try {
			usdb.addUser(user.getUserName(), user);
			usdb.addUser(user2.getUserName(), user2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("USERDATABASE:\n" + usdb.getUserDataBase());
		System.out.println(usdb.getUser("Thomas"));
		System.out.println(usdb.getUser("Thomas").getPassword());
		System.out.println("################USER DATABASE TEST ENDING #############\n");
	}

	public static void test_SeatDatabase() {
		System.out.println("#############	SEAT DATABASE TESTING ############");
		String date = "2020-12-23";
		String time = "06_30_PM";
		double seatPrice = 35.00;
		SeatDatabase<Seat> sdb = new SeatDatabase<Seat>(date, time);
		for (int i = 0; i < 10; i++) {
			sdb.addSeat(new Seat("m", i, seatPrice, false, date, time));
		}
		System.out.println("GET SEAT DATABASE: " + sdb.getSeatDataBase());
		System.out.println("GET SEAT ID: " + sdb.getSeatID("m1"));
		sdb.getSeatID("m1").setIsOccupied(true);
		System.out.println("GET SEAT ID: " + sdb.getSeatID("m1"));
		System.out.println("SEAT DATE TIME: " + sdb.getSeatID("m1").getSeatDateTime());
		System.out.println(sdb.toString());
		System.out.println(sdb.getSeatID("m1").getSeatSection());
		System.out.println("############ ENDING SEAT DATABASE TESTING #############");
	}

	public static void test_BuildShowDB() {
		String seatData = seats_filepath;
		System.out.println("############ SHOW_DATABASE BEGIN TESTING ############");
		String date = "2020-12-23";
		String time = "06_30_PM";
		String datetime2 = "2020-12-24|06_30_PM";
		SeatDatabase<Seat> sdb = new SeatDatabase<Seat>(date, time);
		double seatPrice = 35.00;
		for (int i = 0; i < 10; i++) {
			sdb.addSeat(new Seat("m", i, seatPrice, false, date, time));
		}
//		System.out.println(sdb.getSeatDataBase());
		ShowDatabase<SeatDatabase<Seat>> shwDB = new ShowDatabase<>();
		shwDB.addShow(sdb.getDateTime(), sdb);
//		System.out.println(shwDB.getShowDatabase());
//		System.out.println(shwDB.getShowID(date + "|" + time));

		FileIO io = new FileIO();
		ShowDatabase<SeatDatabase<Seat>> show = io.buildShowDataBase(seatData);
//		System.out.println(show.getShowDatabase());

//		System.out.println(show.getShowID(date + "|" + time).getSeatID("m1"));

		System.out.println(show.getShowID(datetime2));

		System.out.println("############ SHOW_DATABASE END TESTING ############");

	}

	public static void test_AddReservations(String filePath) throws IOException {
		String date = "2020-12-23";
		String time = "06_30_PM";
		String dateTime = date + "|" + time;
		double seatPrice = 35.00;
		String seatID = "m1";
		String seatID2 = "m2";
		String seatID3 = "m3";

		FileIO io = new FileIO();
		UserDatabase<User> usdb = io.BuildUserDatabase(filePath);
		User user = usdb.getUser("Thomas");
		user.addReservations(dateTime, seatID);
//		System.out.println(user.getReservations(dateTime));
		user.addReservations(dateTime, seatID3);
		user.addReservations(dateTime, seatID2);
		user.addReservations(dateTime, seatID3);
		System.out.println(user);

	}

	public static void test_RemoveSeatReservations(String filePath) {
		String date = "2020-12-23";
		String time = "06_30_PM";
		String dateTime = date + "|" + time;
		double seatPrice = 35.00;
		String seatID = "m1";
		String seatID2 = "m2";
		String seatID3 = "m3";

		FileIO io = new FileIO();
		UserDatabase<User> usdb = io.BuildUserDatabase(filePath);
		User user = usdb.getUser("Thomas");
		user.addReservations(dateTime, seatID);
		System.out.println(user.getReservation(dateTime));
		user.addReservations(dateTime, seatID3);
		user.addReservations(dateTime, seatID2);
		user.addReservations(dateTime, seatID3);
		System.out.println(user);
		user.removeSeatReservation(dateTime, seatID3);
		user.removeSeatReservation(dateTime, seatID2);
		System.out.println(user);
	}

	public static void test_FILEIO_buildUserDataBase(String filePath) {
		FileIO io = new FileIO();
		UserDatabase<User> usdb = io.BuildUserDatabase(filePath);
		System.out.println(usdb.getUserDataBase());
		System.out.println(usdb.getUser("Thomas"));
		System.out.println(usdb.getKeys());
	}

	public static void test_FILEIO_buildSeatDataBase(String filePath) {
		// get user database
		FileIO io = new FileIO();
//		UserDatabase<User> usdb = io.BuildUserDatasFbase(filePath);
		// get seat database
		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(filePath);
//		System.out.println(shwDB.getShowDatabase());
		System.out.println(shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m3"));
	}

	public static void viewAllReservations(String filePath) {
		String date = "2020-12-23";
		String time = "06_30_PM";
		String dateTime = date + "|" + time;
		String seatID = "m1";
		String seatID2 = "m2";
		String seatID3 = "m3";

		String date2 = "2020-12-24";
		String time2 = "08_30_PM";
		String dateTime2 = date2 + time2;
		String seat1 = "m1";
		String seat2 = "eb1";
		String seat3 = "sb2";

		FileIO io = new FileIO();
		UserDatabase<User> usdb = io.BuildUserDatabase(filePath);
		User user = usdb.getUser("Thomas");
		user.addReservations(dateTime, seatID);
//		System.out.println(user.getReservations(dateTime));
		user.addReservations(dateTime, seatID3);
		user.addReservations(dateTime, seatID2);
//		System.out.println("USER RESERVATIONS: " + user.viewAllReservations(dateTime));
		user.addReservations(dateTime2, seat1);
		user.addReservations(dateTime2, seat2);
		user.addReservations(dateTime2, seat3);
//		System.out.println(user.getReservations(dateTime));
//		System.out.println(user.getReservations(dateTime2));
		System.out.println(user);
	}

	public static void test_makingReservations() {
		String user_Reservations = reservation_filepath;
		String seatData = seats_filepath;
		String testData = seatTest_Filepath;
		FileIO io = new FileIO();
		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(testData);
		// we are gonna extract data from shwDB

		UserDatabase<User> usdb = new UserDatabase<User>();
		User user = new User("Thomas", "Lao");
		usdb.addUser(user.getUserName(), user);
		Seat s = shwDB.getShowID("2020-12-23", "06_30_PM").getSeatID("m1");
		Seat s2 = shwDB.getShowID("2020-12-23", "06_30_PM").getSeatID("m2");
//		Seat s3 = shwDB.getShowID("2020-12-23", "06_30_PM").getSeatID("m2");

//		System.out.println(s2);

		user.addReservations(s);
		shwDB.getShowID(s.getSeatDateTime()).getSeatID(s.getSeatID()).setIsOccupied(true);
		user.addReservations(s2);
		shwDB.getShowID(s2.getSeatDateTime()).getSeatID(s2.getSeatID()).setIsOccupied(true);
		System.out.println(shwDB);
//		user.addReservations(s3);
//		shwDB.getShowID(s3.getSeatDateTime()).getSeatID(s3.getSeatID()).setIsOccupied(true);
//		user.addReservations(s4);
//		shwDB.getShowID(s4.getSeatDateTime()).getSeatID(s4.getSeatID()).setIsOccupied(true);
//		user.addReservations(s2);
//		user.addReservations(s3);
//		user.addReservations(s4);
		user.addReservations(s);
		user.addReservations(s2);
		System.out.println(user);
//		System.out.println(shwDB);
//		System.out.println(user);

//		System.out.println(shwDB);
//		// this is going to need to be iterated somehow or everytime a seat is added
//		// we add the seat
//		user.addReservations(s);
//		// seat's occupied status is updated
//		// constraint will cover the repeated reservation
//		shwDB.getShowID(s.getSeatDateTime()).getSeatID(s.getSeatID()).setIsOccupied(true);
//		// add again and watch it FAIL
//		user.addReservations(s);

		/*
		 * Enter my username and password
		 */
//		UserDatabase<User> usdb = io.BuildUserDatabase(user_Reservations);
//		User user = usdb.getUser("Thomas");
		/**
		 * some functionality here when user enters date and time into GUI displays the
		 * list of seats of that time
		 */
//		System.out.println(shwDB.getShowID("2020-12-23", "06_30_PM"));
//		// pick a seat
//		Seat s = shwDB.getShowID("2020-12-23", "06_30_PM").getSeatID("m1");
//		user.addReservations(s);
//		System.out.println(user);

//		System.out.println(user.getReservation(s.getSeatDateTime()));
//
//		user.removeSeatReservation(s);
//		System.out.println(user);

//		user.addReservations(s.getSeatDateTime(), s.getSeatID());

//		System.out.println(s.getSeatDateTime());
//		System.out.println(s.getSeatID());

	}

}
