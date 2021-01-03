package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import model.Seat;
import model.SeatDatabase;
import model.User;
import model.UserDatabase;

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

	public void saveSeatDatabaseToText(String fileName, SeatDatabase<Seat> sdb) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println("#######################		FILE WRITE BEGIN	##############################");

	}

	// LOADS SEAT DATABASE INFORMATION
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
		String DatesStr, TimeStr;
//#######################DATES,TIME INFORMATION##############
		DatesStr = prop.getProperty("dates");
		TimeStr = prop.getProperty("times");
//		System.out.println(DatesStr);
		String[] timesArray = TimeStr.split(",");
		String[] datesArray = DatesStr.split(",");
		// ##############SEATDATABASE INFORMATION#############
		SeatDatabase<Seat> sdb = new SeatDatabase<>();
		// ################Data Parsing Attributes for Sections###########
//		String sectionM;
//		String sectionSB;
//		String sectionWB;
//		String sectionEB;
		String seatSection = "";
		String seatSectionTime = "";
		String seatSectionDate = "";
		String seatSectionStartnmbr = "";
		String seatSectionEndnmbr = "";
		String seatSectionPrice = "";
		String DateTime = "";
		String seatID = "";
		// ####################SectionNames: m,sb,wb,eb###############

		String sectionNamesSplit = prop.getProperty("seatSections");
		String[] sectionNames_Array = sectionNamesSplit.split(",");
		// I WANT MY SEATNAMES. ILL BE USING THEM FOR THE SEATID LATER
		String seat_m = sectionNames_Array[0];
		String seat_sb = sectionNames_Array[1];
		String seat_wb = sectionNames_Array[2];
		String seat_eb = sectionNames_Array[3];
		// ###########################################################
		ArrayList<String> seatSectionInformation_Array = new ArrayList<>();
		/**
		 * ############ LET'S GET OUR SECTION NAMES,GET THE SECTION INFORMATION########
		 * ############ AND THE SEAT SECTION INFORMATION INTO THE ARRAYLIST
		 */

		for (String sectionName : sectionNames_Array) {
			seatSection = prop.getProperty(sectionName);
			seatSectionInformation_Array.add(seatSection);
		}
		/*
		 * ############# ONCE OUR SEAT SECTION INFORMATION HAS BEEN STOREd ######
		 * ############# LET'S PARSE SOME MORE INFORMATION ############# #############
		 * HERE WE USE MODULUS 3 TO TOKENIZE OUR SEAT INFORMATION ITERATING THROUGH OUR
		 * ARRAY: SEAT_START#,SEAT_END#,SEATPRICE
		 * 
		 */

		for (String seat : seatSectionInformation_Array) {

			String[] seatSectionInformation_Array_Split = seat.split("[,|]");
			for (int i = 0; i < seatSectionInformation_Array_Split.length; i++) {
				if (i % 3 == 0) {
					System.out.println("Start:" + seatSectionInformation_Array_Split[i]);
					seatSectionStartnmbr = seatSectionInformation_Array_Split[i];
				} else if (i % 3 == 1) {
					System.out.println("End:" + seatSectionInformation_Array_Split[i]);
					seatSectionEndnmbr = seatSectionInformation_Array_Split[i];
				} else if (i % 3 == 2) {
					System.out.println("Price:" + seatSectionInformation_Array_Split[i]);
					seatSectionPrice = seatSectionInformation_Array_Split[i];
				}

//				for (int k = 0; k < datesArray.length; k++) {
//					datesArray[k] = datesArray[k].strip();
//					System.out.println(datesArray[k]);
//				}

			}
			// INCLUSION OF PARSED DATETIME INFORMATION

		}

		for (int k = 0; k < datesArray.length; k++) {
			datesArray[k] = datesArray[k].strip();
			seatSectionDate = datesArray[k];
//			System.out.println(seatSectionDate);
			for (int j = 0; j < timesArray.length; j++) {
				timesArray[j] = timesArray[j].strip();

				seatSectionTime = timesArray[j];
//				System.out.println(seatSectionTime);
				DateTime = seatSectionDate + "|" + seatSectionTime;
//				System.out.println(DateTime);
				for (String s : sectionNames_Array) {
					seatID = s + DateTime;
					System.out.printf(seatID + "\n");
				}

			}
			System.out.println("##");

		}

//		seatID = sectionTime + "|" + sectionDate + "|" + seat_m + seatStartnmbr;
//		System.out.println("SeatIDs" + seatID);

//		for (int j = 0; j < timesArray.length; j++) {
//		timesArray[i] = timesArray[i].strip();
//		sectionTime = timesArray[i];
//
//		for (int k = 0; k < datesArray.length; k++) {
//			datesArray[i] = datesArray[i].strip();
//			sectionDate = datesArray[i];
//		}
//	}

		// ###############LIST FOR HOLDING INFORMATION FROM EACH SECTION####
		// ex: m = 1,150,35

//		sectionM = prop.getProperty("m");
//		sectionSB = prop.getProperty("sb");
//		sectionWB = prop.getProperty("wb");
//		sectionEB = prop.getProperty("eb");

//		System.out.println(sectionM);
//		String[] sectionM_Array = sectionM.split("[,|]");

//		sectionsList.add(sectionM);
//		sectionsList.add(sectionSB);
//		sectionsList.add(sectionWB);
//		sectionsList.add(sectionEB);
//		for (String s : sectionsList) {
//			String[] section_Array = s.split("[,|]");
//			for (int i = 0; i < section_Array.length; i++) {
//				if (i % 3 == 0) {
//					System.out.println("Start:" + section_Array[i]);
//					seatStartnmbr = section_Array[i];
//				} else if (i % 3 == 1) {
//					System.out.println("End:" + section_Array[i]);
//					seatEndnmbr = section_Array[i];
//				} else if (i % 3 == 2) {
//					System.out.println("Price:" + section_Array[i]);
//					seatPrice = section_Array[i];
//				}
//
//			}
//		}
//		for (int i = 0; i < sectionM_Array.length; i++) {
//			if (i % 3 == 0) {
////				System.out.println("Start:" + sectionM_Array[i]);
//				seatStartnmbr = sectionM_Array[i];
//			} else if (i % 3 == 1) {
////				System.out.println("End:" + sectionM_Array[i]);
//				seatEndnmbr = sectionM_Array[i];
//			} else if (i % 3 == 2) {
////				System.out.println("Price:" + sectionM_Array[i]);
//				seatPrice = sectionM_Array[i];
//			}
//		}

//		String[] sectionSB_Array = sectionSB.split("[,|]");

//		System.out.println("SIZE" + sectionSB_Array.length);
//		for (int i = 0; i < sectionSB_Array.length; i++) {
//			if (i % 3 == 0) {
//				seatStartnmbr = sectionM_Array[i];
//			} else if (i % 3 == 1) {
//				seatEndnmbr = sectionM_Array[i];
//				seatPrice = sectionM_Array[i];
//			} else if (i % 3 == 2) {
//			}
////			
//		}
//		String[] sectionWB_Array = sectionWB.split("[,|]");
//		for (int i = 0; i < sectionWB_Array.length; i++) {
//////		sectionSB_Array[i] = sectionSB_Array[i].strip();
//			if (i % 3 == 0) {
//				seatStartnmbr = sectionWB_Array[i];
////			System.out.println("Start:" + sectionSB_Array[i]);
//			} else if (i % 3 == 1) {
//				seatEndnmbr = sectionWB_Array[i];
////			System.out.println("End:" + sectionSB_Array[i]);
//			} else if (i % 3 == 2) {
//				seatPrice = sectionWB_Array[i];
////			System.out.println("Price:" + sectionSB_Array[i]);
//			}
//		}
////		
//		String[] sectionEB_Array = sectionEB.split("[,|]");
//		for (int i = 0; i < sectionEB_Array.length; i++) {
//			if (i % 3 == 0) {
//				seatStartnmbr = sectionEB_Array[i];
//			} else if (i % 3 == 1) {
//				seatEndnmbr = sectionEB_Array[i];
//			} else if (i % 3 == 2) {
//				seatPrice = sectionEB_Array[i];
//			}
//		}
//		

//
//		// #######################END DATE CREATION#####################

		return null;
	}

	// ###################################################################

	public static void main(String args[]) throws IOException {

////		String filePath = "reservations.txt";
//		String filePath = "seats.txt";
//		String userFilePath = "reservations.txt";

		String filePath = "seatDB.txt";
		FileIO io = new FileIO();
		io.buildSeatDataBase(filePath);

////		io.BuildSeatDatabaseToText(filePath);
//
//////		io.loadUserDBFromProperty(filePath);
////////		String filePath = "reservations.txt";
////////		Properties rp = new Properties();
//		UserDatabase<User> usdb = new UserDatabase<User>();
//		usdb.addUser("Thomas", new User("Thomas", "123456"));
//		usdb.addUser("Jake", new User("Jake", "78910"));
//		usdb.addUser("Henry", new User("Henry", "234567"));
//		io.saveUserDatabaseToText(userFilePath, usdb);
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
		Seat seat1 = new Seat(35.0, 1, false, "m", "2020-12-24", "06:30PM");
//		sdb.addSeat("0_m1", seat1);
//		Seat seat2 = new Seat(35.0, 1, false, "n", "2020-12-25", "06:30PM");
//		sdb.addSeat("1_n2", seat2);
//		Seat seat3 = new Seat(35.0, 1, false, "m", "2020-12-26", "06:30PM");
//		sdb.addSeat("2_m1", seat3);
////		io.buildSeatDataBase(filePath);
//		io.saveSeatDatabaseToText(filePath, sdb);

	}

}
