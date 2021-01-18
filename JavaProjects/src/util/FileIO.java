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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Seat;
import model.SeatDatabase;
import model.ShowDatabase;
import model.User;
import model.UserDatabase;

public class FileIO {
	/*
	 * make sure our date and time are in the correct format
	 * 
	 */

	private DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private DateTimeFormatter time_formatter = DateTimeFormatter.ofPattern("hh_mm_a");

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
			e.printStackTrace();
		}
		return null;
	}

	// helper method
	private UserDatabase<User> BuildUserDB(Properties prop) {
		int userCount = 0;
		int reservationCount = 0;
		// let's initialize an empty userdatabase
		UserDatabase<User> usdb = new UserDatabase<User>();
		while (prop.getProperty("user_" + userCount) != null
				&& prop.getProperty("reservations_" + reservationCount) != null) {
			String username = prop.getProperty("user_" + userCount);
			String password = prop.getProperty("password_" + userCount);
			usdb.addUser(username, new User(username, password));
			userCount++;
		}
		// let's load our seat reservations: seatIDs, date,, time
		while (prop.getProperty("user_" + userCount) != null
				&& prop.getProperty("reservations_" + reservationCount) != null) {
			String username = prop.getProperty("user_" + userCount);
			String password = prop.getProperty("password_" + userCount);
			String reservations = prop.getProperty("reservations_" + reservationCount);
			Pattern p = Pattern.compile("\\:([^:]*)\\:");
			Matcher m = p.matcher(reservations);
			while (m.find()) {
				String dateTime = m.group(1);
				String seats = usdb.getUser(username).getReservation(dateTime).toString().replaceAll("[\\[\\]]", "")
						.trim();
				String[] reservedSeatsSplit = seats.split(",");
				for (String seat : reservedSeatsSplit) {
						
				}
			}
			userCount++;
			reservationCount++;

		}

		return usdb;

	}

	public void saveUserDatabaseToText(String fileName, UserDatabase<User> usdb) throws IOException {
		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println("#######################		FILE WRITE BEGIN	##############################");
		String dataBaseCollection = usdb.getUserDataBase().toString();
		System.out.println(dataBaseCollection);
		bw.write(dataBaseCollection);

		System.out.println("#######################		FILE WRITE ENDED	##############################");
		bw.close();
	}

//	public UserDatabase<User> BuildReservationDatabase(String filePath) {
//		Properties prop = new Properties();
//
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(filePath);
//			prop.load(fis);
//			return BuildReservationDB(prop);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	private UserDatabase<User> BuildReservationDB(Properties prop) {
//		int reservationCount = 0;
//		int userCount = 0;
//		
//		
//	}

	// may not need
//	public void saveSeatDatabaseToText(String fileName, SeatDatabase<Seat> sdb) throws IOException {
//		FileWriter fw = new FileWriter(fileName);
//		BufferedWriter bw = new BufferedWriter(fw);
//		System.out.println("#######################		FILE WRITE BEGIN	##############################");
//	}

	// LOADS SEAT DATABASE INFORMATION
	// this method ,acced by time and date, will be the list of shows
	// available for the user to choose
	public ShowDatabase<SeatDatabase<Seat>> buildShowDataBase(String filePath) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			return buildShowDB(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// helper method
	private ShowDatabase<SeatDatabase<Seat>> buildShowDB(Properties prop) {
		String DatesStr, TimeStr;
//#######################DATES,TIME INFORMATION##############
		DatesStr = prop.getProperty("dates");
		TimeStr = prop.getProperty("times");
//		System.out.println(DatesStr);
		String[] timesArray = TimeStr.split(",");
		String[] datesArray = DatesStr.split(",");
		// ##############SEATDATABASE INFORMATION#############
//		SeatDatabase<Seat> sdb = new SeatDatabase<>();
		// ################Data Parsing Attributes for Sections###########
//		String sectionM;
//		String sectionSB;
//		String sectionWB;
//		String sectionEB;
		String seatSectionInfo = "";
		String seatSectionTime = "";
		String seatSectionDate = "";
		String seatSectionStartNmbr_String = "";
		String seatSectionEndNmbr_String = "";
		String seatSectionPrice_String = "";
		String seatID = "";
		// ####################SectionNames: m,sb,wb,eb###############

		String sectionNames = prop.getProperty("seatSections");
		String[] sectionNames_Array = sectionNames.split(",");
//		for (String s : sectionNames_Array)
//			System.out.println(s);
		// I WANT MY SEATNAMES. ILL BE USING THEM FOR THE SEATID LATE
		// ###########################################################
//		ArrayList<String> seatSectionInformation_Array = new ArrayList<>();
		/**
		 * ############ LET'S GET OUR SECTION NAMES,GET THE SECTION INFORMATION########
		 * ############ AND THE SEAT SECTION INFORMATION INTO THE ARRAYLIST
		 */
		// create showDatabase from date and times
		// iter date
		// iter time
		// create and add a new seatDatabase
		ShowDatabase<SeatDatabase<Seat>> shwDB = new ShowDatabase<SeatDatabase<Seat>>();
		for (int j = 0; j < datesArray.length; j++) {
			datesArray[j] = datesArray[j].strip();
			seatSectionDate = datesArray[j];
			for (int k = 0; k < timesArray.length; k++) {
				timesArray[k] = timesArray[k].strip();
				seatSectionTime = timesArray[k];
				SeatDatabase<Seat> sdb = new SeatDatabase<Seat>(seatSectionDate, seatSectionTime);
				String DateTime = seatSectionDate + "|" + seatSectionTime;
				shwDB.addShow(DateTime, sdb);
			}
		}

		// create collection of seats to be added into showDatabase
		// access by date and time to get the seatDatabase to add seats
		// iter sections
		for (int g = 0; g < sectionNames_Array.length; g++) {
			seatSectionInfo = prop.getProperty(sectionNames_Array[g]);

			String[] seatTuples = seatSectionInfo.split("[|]");
			// iter start, end, price of seat numbers pertaining to a section
			for (int h = 0; h < seatTuples.length; h++) {
				String[] elems = seatTuples[h].split(",");

				String start, end, price;
				start = elems[0];
				end = elems[1];
				price = elems[2];
				int START = Integer.parseInt(start);
				int END = Integer.parseInt(end);
				double PRICE = Double.parseDouble(price);

				// generate seat number from start and end
				for (; START <= END; START++) {
					for (int j = 0; j < datesArray.length; j++) {
						datesArray[j] = datesArray[j].strip();
						seatSectionDate = datesArray[j];
						if (isValidDate(seatSectionDate)) {
							for (int k = 0; k < timesArray.length; k++) {
								timesArray[k] = timesArray[k].strip();
								seatSectionTime = timesArray[k];
								if (isValidTime(seatSectionTime)) {

									// call read method from showDatabase to get a seatDatabase from a given
									// datetimeStr
									// add the seat into the seatDatabase

//									String seatToAdd = sectionNames_Array[g] + "|" + START + "|" + PRICE + "|" + seatSectionDate
//											+ "|" + seatSectionTime;
									seatID = sectionNames_Array[g] + START;
									String DateTime = seatSectionDate + "|" + seatSectionTime;
									shwDB.getShowID(DateTime).addSeat(seatID, new Seat(sectionNames_Array[g], START,
											PRICE, false, seatSectionDate, seatSectionTime));
								}

							}
						}

					}

				}

			}
		}

		return shwDB;

	}

	/*
	 * GOING TO NEED BUILD_RESERVATION_FUNCTION
	 * 
	 * GOING TO NEED SAVE_RESERVATION_TO_TEXT_FUNCTION
	 * 
	 */

//	public void saveShowDataBaseToText(String fileName, ShowDatabase<SeatDatabase<Seat>> showDatabase)
//			throws IOException {
//		FileWriter fw = new FileWriter(fileName, true);
//		BufferedWriter bw = new BufferedWriter(fw);
//		System.out.println("#######################		FILE WRITE BEGIN	##############################");
//		String showDataBaseCollection = showDatabase.getShowDatabase().toString().replaceAll("[\\[ ,{}\\]]", "");
//		System.out.println(showDataBaseCollection);
//		bw.write(showDataBaseCollection);
//		System.out.println("#######################		FILE WRITE ENDED	##############################");
//		bw.close();
//	}

	public boolean isValidDate(String date) {
		LocalDate valid = null;
		try {
			valid = LocalDate.parse(date, date_formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}

	}

	public boolean isValidTime(String time) {
		LocalTime valid = null;
		try {
			valid = LocalTime.parse(time, time_formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	// ###################################################################

}
