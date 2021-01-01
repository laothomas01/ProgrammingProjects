import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class FileIO {

//	public UserDatabase<User> loadUserDBFromText(String fileName) throws IOException {
//		UserDatabase<User> usdb = new UserDatabase<User>();
//		BufferedReader br = new BufferedReader(new FileReader(fileName));
//		String line;
//		String username = "";
//		String password = "";
//		int lineCount = 0;
//		while ((line = br.readLine()) != null) {
//			if (lineCount % 2 == 0) {
//				username = line;
//				System.out.println(
//						lineCount + " Line read: [" + line + "] Username: [" + username + "] Password: " + password);
//			} else if (lineCount % 2 == 1) {
//				password = line;
//				System.out.println(lineCount + " Line read: [" + line + "] Username: " + username + " Password: ["
//						+ password + "] ");
//				usdb.addUser(username, new User(username, password));
//			}
//			lineCount++;
//		}
//		br.close();
//		System.out.println("FILE READING ENDED");
//
//		return usdb;
//
//	}

	// LOADING INFORMATION FROM .TXT FILE
	// ###########################USERDATABASE#####################################
	public UserDatabase<User> BuildUserDatabase(String filePath) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			return BuildUserDB(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// helper class
	private UserDatabase<User> BuildUserDB(Properties prop) {
		UserDatabase<User> usdb = new UserDatabase<User>();
		int userCount = 0;
		while (prop.getProperty("user_" + userCount) != null) {
			String password, user;
			password = prop.getProperty("password_" + userCount);
			user = prop.getProperty("user_" + userCount);
			System.out.println(user);
			System.out.println(password);
			usdb.addUser(user, new User(user, password));
			userCount++;
		}

		return usdb;

//		String DatesStr, TimeStr;
//
//		DatesStr = prop.getProperty("dates");
//		TimeStr = prop.getProperty("times");
////		System.out.println(DatesStr);
//		String[] timesArray = TimeStr.split(",");
//		for (int i = 0; i < timesArray.length; i++) {
//			timesArray[i] = timesArray[i].strip();
////					.replaceAll("[_]", ":");
////			System.out.println(timesArray[i]);
//		}
//
//		String[] datesArray = DatesStr.split(",");
//		for (int i = 0; i < datesArray.length; i++) {
////			String temp;
////			temp = datesArray[i].strip();
////			System.out.println(datesArray[i]);
//			datesArray[i] = datesArray[i].strip();
////			System.out.println(datesArray[i]);
//		}

	}

	public void saveUserDatabaseToText(String fileName, UserDatabase<User> usdb) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println("#######################		FILE WRITE BEGIN	##############################");
		String dataBaseCollection = usdb.getUserDataBase().toString().replaceAll("[\\[ ,\\]]", "");
		System.out.println(dataBaseCollection);
		bw.write(dataBaseCollection);
		System.out.println("#######################		FILE WRITE ENDED	##############################");
		bw.close();
	}

	
	//LOADS SEAT DATABASE INFORMATION
	public SeatDatabase<Seat> buildSeatDataBase(String filePath) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			return buildSeatDB(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private SeatDatabase<Seat> buildSeatDB(Properties prop) {
		
//		String seatPrice, seatNumber, isOccupied, seatSection, seatDate, seatTime;
//		seatPrice = prop.getProperty("seatPrice");
//
//		seatNumber = prop.getProperty("seatNumber");
//		isOccupied = prop.getProperty("isOccupied");
//		seatSection = prop.getProperty("seatSection");
//		seatDate = prop.getProperty("seatDate");
//		seatTime = prop.getProperty("seatTime");
////		String[] seatPriceArray = seatPrice.split(" ");
////		for (int i = 0; i < seatPriceArray.length; i++) {
////			seatPriceArray[i] = seatPriceArray[i].strip();
////			System.out.println(seatPriceArray[i]);
////		}
//		System.out.println(seatPrice);
//		System.out.println(seatNumber);
//		System.out.println(isOccupied);
//		System.out.println(seatSection);
//		System.out.println(seatDate);
//		System.out.println(seatTime);
		return null;
	}

	public void saveSeatDatabaseToText(String fileName) throws IOException {
		
//		FileWriter fw = new FileWriter(fileName);
//		BufferedWriter bw = new BufferedWriter(fw);
//		System.out.println("#######################		FILE WRITE BEGIN	##############################");
//		// write in dates later
//		bw.write(
//				"dates = 2020-12-23,2020-21-24,\n2020-12-25,2020-21-26\n2020-12-29,2020-12-30\n2020-12-31,2021-01-01\n");
//		// write in show times
//		bw.write("times=06_30_PM,08_30_PM");
//		// main floor
//		for (int i = 1; i < 51; i++) {
//			System.out.printf("m" + Integer.toString(i) + "=35\n");
//			bw.write("m" + Integer.toString(i) + "=35\n");
//		}
//		for (int i = 51; i < 101; i++) {
//			System.out.printf("m" + Integer.toString(i) + "=35\n");
//			bw.write("m" + Integer.toString(i) + "=35\n");
//		}
//		for (int i = 101; i < 151; i++) {
//			System.out.printf("m" + Integer.toString(i) + "=35\n");
//			bw.write("m" + Integer.toString(i) + "=35\n");
//		}
//
//		// south balcony
//		for (int i = 1; i < 26; i++) {
//			System.out.printf("s" + Integer.toString(i) + "=50\n");
//			bw.write("sb" + Integer.toString(i) + "=50\n");
//		}
//		for (int i = 26; i < 51; i++) {
//			System.out.printf("s" + Integer.toString(i) + "=55\n");
//			bw.write("sb" + Integer.toString(i) + "=55\n");
//		}
//		// west balcony
//		for (int i = 1; i < 101; i++) {
//			System.out.println("wb" + Integer.toString(i) + "=40\n");
//			bw.write("wb" + Integer.toString(i) + "=40\n");
//		}
//		// easy balcony
//		for (int i = 1; i < 101; i++) {
//			System.out.println("eb" + Integer.toString(i) + "=40\n");
//			bw.write("eb" + Integer.toString(i) + "=40\n");
//		}
//
//		System.out.println("#######################		FILE WRITE ENDED	##############################");
//		bw.close();
	}

	// ###################################################################

	public static void main(String args[]) throws IOException {

//		String filePath = "reservations.txt";
		String filePath = "seats.txt";
		String userFilePath = "reservations.txt";
		FileIO io = new FileIO();
//		io.BuildSeatDatabaseToText(filePath);

////		io.loadUserDBFromProperty(filePath);
//////		String filePath = "reservations.txt";
//////		Properties rp = new Properties();
		UserDatabase<User> usdb = new UserDatabase<User>();
		usdb.addUser("Thomas", new User("Thomas", "123456"));
		usdb.addUser("Jake", new User("Jake", "78910"));
		usdb.addUser("Henry", new User("Henry", "234567"));
		io.saveUserDatabaseToText(userFilePath, usdb);

//		FileIO.testLoadFromTextFile();
//		io.buildSeatDataBase(filePath);
	}

	public static void testLoadFromTextFile() throws IOException {
//		String filePath = "reservations.txt";
////		String Filepath = "reservation.txt";
//		FileIO io = new FileIO();
////		UserDatabase<User> usdb = new UserDatabase<User>();
////		usdb.addUser("Thomas", new User("Thomas", "Lao"));
////		io.saveUserDatabaseToText(filePath, usdb);
//		SeatDatabase<Seat> sdb = new SeatDatabase<Seat>();
//		Seat seat1 = new Seat(35.0, 1, false, "m", "2020-12-24", "06:30PM");
//		sdb.addSeat("0_m1", seat1);
//		Seat seat2 = new Seat(35.0, 1, false, "n", "2020-12-25", "06:30PM");
//		sdb.addSeat("1_n2", seat2);
//		Seat seat3 = new Seat(35.0, 1, false, "m", "2020-12-26", "06:30PM");
//		sdb.addSeat("2_m1", seat3);
////		io.buildSeatDataBase(filePath);
//		io.saveSeatDatabaseToText(filePath, sdb);

	}

}
