package driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//		Main.test_AddReservations();
//		Main.test_FILEIO_buildSeatDataBase(seatData);
//		Main.test_RemoveSeatReservations(user_Reservations);
//		Main.viewAllReservations(user_Reservations);
//		FileIO io = new FileIO();
//		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(seatData);
//		System.out.println(shwDB.getShowDatabase());
//		Main.test_makingRes ervations();

//		Main.test_FILEIO_buildUserDataBase(user_Reservations);
//		Main.test_FILEIO_buildUserDatabase();
//		Main.test_BuildUserDatabaseIO();
//		Main.test_UserDatabase();
//		FileIO io = new FileIO();
//		UserDatabase<User> usdb = io.BuildUserDatabase(user_Reservations);
//		String info = "{2020-12-23|08_30_PM=[m4], 2020-12-24|06_30_PM=[m1,m2]}";
////		Pattern p = Pattern.compile("(\\d+-\\d+-\\d+)|(\\d+_\\d+)_(AM|PM)");
//		Pattern p = Pattern.compile("(\\d+-\\d+-\\d+)\\|(\\d+_\\d+)_(AM|PM)\\=\\[([^]]*)\\]");
//		Matcher m = p.matcher(info);
//		while (m.find()) {
////			System.out.println(m.group(1) + " " + m.group(2));
////			System.out.println(m.group(3));
//			String[] seats = m.group(4).split(",");
//			for (int i = 0; i < seats.length; i++) {
//				System.out.println(m.group(1) + " " + m.group(2) + " " + m.group(3) + " " + seats[i]);
//			}
//		}

////		m.find();
////		System.out.println(m.group(1));

		UserDatabase<User> usdb = new UserDatabase<User>();

		User user = new User("Thomas", "Lao");
		User user2 = new User("Jake", "Paul");
		FileIO io = new FileIO();
		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(seatData);
		// create some dummy seats
		Seat s1 = shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m1");
		Seat s2 = shwDB.getShowDate("2020-12-23|08_30_PM").getSeatID("m1");
		Seat s3 = shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m2");

		Seat s4 = shwDB.getShowDate("2020-12-24|06_30_PM").getSeatID("m1");
		Seat s5 = shwDB.getShowDate("2020-12-24|08_30_PM").getSeatID("m1");
		Seat s6 = shwDB.getShowDate("2020-12-24|06_30_PM").getSeatID("m2");
		// add dummy data to user
		user.addReservations(s1);
		user.addReservations(s2);
		user.addReservations(s3);

		user2.addReservations(s4);
		user2.addReservations(s5);
		user2.addReservations(s6);

		// add dummy user to dummy database
		usdb.addUser(user.getUserName(), user);
		usdb.addUser(user2.getUserName(), user2);
		io.saveUserDatabaseToText(user_Reservations, usdb);
		UserDatabase<User> usdbLoad = io.BuildUserDatabase(user_Reservations);
		System.out.println();
		System.out.println("############BEGIN LOADING INFORMATION#############");
		System.out.println(usdbLoad);
		System.out.println("############LOADING HAS FINISHED##################");
//		Main.test_FILEIO_buildUserDatabase();

	}

	public static void test_UserDatabase() throws IOException {
		String seatData = seats_filepath;
		String user_Reservations = reservation_filepath;
		// create some dummy users
		UserDatabase<User> usdb = new UserDatabase<User>();

		User user = new User("Thomas", "Lao");
		User user2 = new User("Jake", "Paul");
		FileIO io = new FileIO();
		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(seatData);
		// create some dummy seats
		Seat s1 = shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m1");
		Seat s2 = shwDB.getShowDate("2020-12-23|08_30_PM").getSeatID("m1");
		Seat s3 = shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m2");

		Seat s4 = shwDB.getShowDate("2020-12-24|06_30_PM").getSeatID("m1");
		Seat s5 = shwDB.getShowDate("2020-12-24|08_30_PM").getSeatID("m1");
		Seat s6 = shwDB.getShowDate("2020-12-24|06_30_PM").getSeatID("m2");
		// add dummy data to user
		user.addReservations(s1);
		user.addReservations(s2);
		user.addReservations(s3);

		user2.addReservations(s4);
		user2.addReservations(s5);
		user2.addReservations(s6);

		// add dummy user to dummy database
		usdb.addUser(user.getUserName(), user);
		usdb.addUser(user2.getUserName(), user2);
//		System.out.println(usdb.getUser(user.getUserName()).getReservation("2020-12-23|06_30_PM"));
//		System.out.println(user.getReservation("2020-12-23|06_30_PM"));

		/*
		 * ERROR ALERT! RESERVATION GET METHOD DOES NOT WORK! UPDATE: PROBABLY WORKS!
		 */

//		io.saveUserDatabaseToText(user_Reservations, usdb);
		UserDatabase<User> tempStorage = new UserDatabase<User>();
		int userCount = 0;
		int reservationCount = 0;
		FileInputStream fis = new FileInputStream(user_Reservations);
		Properties prop = new Properties();
		prop.load(fis);
		while (prop.getProperty("user_" + userCount) != null
				&& prop.getProperty("reservations_" + reservationCount) != null) {
			String username = prop.getProperty("user_" + userCount);
			String password = prop.getProperty("password_" + userCount);
			String reservations = prop.getProperty("reservations_" + reservationCount);
//			System.out.println(username);
//			System.out.println(password);
//			System.out.println(reservations);

			Pattern p = Pattern.compile("\\:([^:]*)\\:");
			Matcher m = p.matcher(reservations);
			while (m.find()) {
				String dateTime = m.group(1);
				System.out.println(dateTime);
//				System.out.println(username);
				String seats = usdb.getUser(username).getReservation(dateTime).toString().replaceAll("[\\[\\]]", "")
						.trim();
				String[] reservedSeatsSplit = seats.split(",");
				System.out.println(usdb.getUser(username).getReservation(dateTime));
//				for (String seat : reservedSeatsSplit) {
//					
//				}
//			 
//				System.out.println(user2.getReservation(dateTime));
//				System.out.println(reservedSeats);
			}
//			while (m.find()) {
//				String dateTime = m.group(1);
//				String reservedSeats = usdb.getUser(username).getReservation(dateTime).toString()
//						.replaceAll("[\\[\\]]", "").trim();
////				System.out.println(dateTime);
//
//				// String[] reservedSeatsSplit = reservedSeats.trim().split(",");
////				for (int i = 0; i < reservedSeatsSplit.length; i++) {
////					seat = reservedSeatsSplit[i].trim();
////
////				}
//			}

			userCount++;
			reservationCount++;
		}
		System.out.println(tempStorage);

	}

//	public static void test_UserDatabase() {
//		System.out.println("##############USER DATABASE TESTING###########");
//		UserDatabase<User> usdb = new UserDatabase<User>();
//		// create a new user
//		User user = new User("Thomas", "Lao");
//		User user2 = new User("Thomas", "Lao");
//		try {
//			usdb.addUser(user.getUserName(), user);
//			usdb.addUser(user2.getUserName(), user2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("USERDATABASE:\n" + usdb.getUserDataBase());
//		System.out.println(usdb.getUser("Thomas"));
//		System.out.println(usdb.getUser("Thomas").getPassword());
//		System.out.println("################USER DATABASE TEST ENDING #############\n");
//	}
//
//	public static void test_SeatDatabase() {
//		System.out.println("#############	SEAT DATABASE TESTING ############");
//		String date = "2020-12-23";
//		String time = "06_30_PM";
//		double seatPrice = 35.00;
//		SeatDatabase<Seat> sdb = new SeatDatabase<Seat>(date, time);
//		for (int i = 0; i < 10; i++) {
//			sdb.addSeat(new Seat("m", i, seatPrice, false, date, time));
//		}
//		System.out.println("GET SEAT DATABASE: " + sdb.getSeatDataBase());
//		System.out.println("GET SEAT ID: " + sdb.getSeatID("m1"));
//		sdb.getSeatID("m1").setIsOccupied(true);
//		System.out.println("GET SEAT ID: " + sdb.getSeatID("m1"));
//		System.out.println("SEAT DATE TIME: " + sdb.getSeatID("m1").getSeatDateTime());
//		System.out.println(sdb.toString());
//		System.out.println(sdb.getSeatID("m1").getSeatSection());
//		System.out.println("############ ENDING SEAT DATABASE TESTING #############");
//	}
//
//	public static void test_BuildShowDB() {
//		String seatData = seats_filepath;
//		System.out.println("############ SHOW_DATABASE BEGIN TESTING ############");
//		String date = "2020-12-23";
//
//		String time = "06_30_PM";
//		String datetime2 = "2020-12-24|06_30_PM";
//		SeatDatabase<Seat> sdb = new SeatDatabase<Seat>(date, time);
//		double seatPrice = 35.00;
//		for (int i = 0; i < 10; i++) {
//			sdb.addSeat(new Seat("m", i, seatPrice, false, date, time));
//		}
////		System.out.println(sdb.getSeatDataBase());
//		ShowDatabase<SeatDatabase<Seat>> shwDB = new ShowDatabase<>();
//		shwDB.addShow(sdb.getDateTime(), sdb);
////		System.out.println(shwDB.getShowDatabase());
////		System.out.println(shwDB.getShowID(date + "|" + time));
//
//		FileIO io = new FileIO();
//		ShowDatabase<SeatDatabase<Seat>> show = io.buildShowDataBase(seatData);
////		System.out.println(show.getShowDatabase());
//
////		System.out.println(show.getShowID(date + "|" + time).getSeatID("m1"));
//
//		System.out.println(show.getShowID(datetime2));
//
//		System.out.println("############ SHOW_DATABASE END TESTING ############");
//
//	}
//
//	public static void test_AddReservations() throws IOException {
//		String date = "2020-12-23";
//		String time = "06_30_PM";
//		String dateTime = date + "|" + time;
//		double seatPrice = 35.00;
//		String seatID = "m1";
//		String seatID2 = "m2";
//		String seatID3 = "m3";
//
//		FileIO io = new FileIO();
////		UserDatabase<User> usdb = io.BuildUserDatabase(filePath);
//
//		User user = new User("Thomas", "Lao");
//
//		System.out.println(user);
//
//	}
//
//	public static void test_RemoveSeatReservations(String filePath) {
//		String date = "2020-12-23";
//		String time = "06_30_PM";
//		String dateTime = date + "|" + time;
//		double seatPrice = 35.00;
//		String seatID = "m1";
//		String seatID2 = "m2";
//		String seatID3 = "m3";
//
//		FileIO io = new FileIO();
//		UserDatabase<User> usdb = io.BuildUserDatabase(filePath);
//		User user = usdb.getUser("Thomas");
//		user.addReservations(dateTime, seatID);
//		System.out.println(user.getReservation(dateTime));
//		user.addReservations(dateTime, seatID3);
//		user.addReservations(dateTime, seatID2);
//		user.addReservations(dateTime, seatID3);
//		System.out.println(user);
//		user.removeSeatReservation(dateTime, seatID3);
//		user.removeSeatReservation(dateTime, seatID2);
//		System.out.println(user);
//	}
//
//	public static void test_FILEIO_buildSeatDataBase(String filePath) {
//		// get user database
//		FileIO io = new FileIO();
////		UserDatabase<User> usdb = io.BuildUserDatasFbase(filePath);
//		// get seat database
//		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(filePath);
////		System.out.println(shwDB.getShowDatabase());
//		System.out.println(shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m3"));
//	}
//
	public static void test_FILEIO_buildUserDatabase() throws IOException {
		/*
		 * String info = "{20F20-12-23|08_30_PM=[m4,m7], 2020-12-24|06_30_PM=[m1,m2]}";
		 * Pattern.compile("(\\d+-\\d+-\\d+)\\|(\\d+_\\d+)_(AM|PM)\\=\\[([^]]*)\\]");
		 * Matcher m = p.matcher(info); while (m.find()) { //
		 * System.out.println(m.group(1) + " " + m.group(2)); //
		 * System.out.println(m.group(3)); System.out.println(m.group(1) + " " +
		 * m.group(2) + " " + m.group(3) + " " + m.group(4)); }
		 * 
		 * 
		 * Console results: group(1) = 2020-12-23 2020-12-24 group(2) = 08_30 06_30
		 * group(3) = AM | PM group(4) = m4,m7 m1,m2
		 * 
		 */

		int userCount = 0;
		int reservationCount = 0;
		String user_Reservations = reservation_filepath;
		String seatData = seats_filepath;
		FileIO io = new FileIO();
		UserDatabase<User> usdb = new UserDatabase<User>();
		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(seatData);
		FileInputStream fis = new FileInputStream(user_Reservations);
		Properties prop = new Properties();
		prop.load(fis);
		while (prop.getProperty("user_" + userCount) != null) {
			String username = prop.getProperty("user_" + userCount);
			String password = prop.getProperty("password_" + userCount);
			usdb.addUser(username, new User(username, password));
			userCount++;
		}
		userCount = 0;
		while (prop.getProperty("user_" + userCount) != null
				&& prop.getProperty("reservations_" + reservationCount) != null) {
			String username = prop.getProperty("user_" + userCount);
			String reservations = prop.getProperty("reservations_" + reservationCount);
//			System.out.println(username);
//			System.out.println(reservations);
			Pattern p = Pattern.compile("(\\d+-\\d+-\\d+)\\|(\\d+_\\d+)_(AM|PM)\\=\\[([^]]*)\\]");
			Matcher m = p.matcher(reservations);
			while (m.find()) {
				String date = m.group(1);
				String time = m.group(2) + "_" + m.group(3);
				String[] seats = m.group(4).strip().split(",");
				for (String seat : seats) {
//					System.out.println(username + " " + date + " " + time + " " + seat);
					usdb.getUser(username).addReservations(date, time, seat);
				}
			}

			userCount++;
			reservationCount++;
		}
		System.out.println(usdb.getUserDataBase());
		System.out.println(usdb.getUser("Thomas"));
		System.out.println(usdb.getUser("Thomas").getReservation("2020-12-23|08_30_PM"));

	}

//
//	public static void test_BuildUserDatabaseIO() {
//		String user_Reservations = reservation_filepath;
//		String seatData = seats_filepath;
//		FileIO io = new FileIO();
//		User user = new User("Thomas", "Lao");
//		ShowDatabase<SeatDatabase<Seat>> shwDB = io.buildShowDataBase(seatData);
//		Seat s1 = shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m1");
//		Seat s2 = shwDB.getShowDate("2020-12-23|06_30_PM").getSeatID("m2");
//		user.addReservations(s1);
//		user.addReservations(s2);
//		UserDatabase<User> usdb = new UserDatabase<>();
//		usdb.addUser(user.getUserName(), user);
//		System.out.println(usdb);
//
//	}

}
