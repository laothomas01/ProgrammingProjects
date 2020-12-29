import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class FileIO {

	public UserDatabase<User> loadUserDBFromText(String fileName) throws IOException {
		UserDatabase<User> usdb = new UserDatabase<User>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		String username = "";
		String password = "";
		int lineCount = 0;
		while ((line = br.readLine()) != null) {
			if (lineCount % 2 == 0) {
				username = line;
				System.out.println(
						lineCount + " Line read: [" + line + "] Username: [" + username + "] Password: " + password);
			} else if (lineCount % 2 == 1) {
				password = line;
				System.out.println(lineCount + " Line read: [" + line + "] Username: " + username + " Password: ["
						+ password + "] ");
				usdb.addUser(username, new User(username, password));
			}
			lineCount++;

		}
		br.close();
		System.out.println("FILE READING ENDED");

		return usdb;

	}

	public void saveToText(String fileName, UserDatabase<User> usdb) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println("FILE WRITE ENDED");
		String dataBaseCollection = usdb.getUserDataBase().toString().replaceAll("[\\[ ,\\]]", "");
		System.out.println(dataBaseCollection);
		bw.write(dataBaseCollection);
		bw.close();

	}

	public UserDatabase<User> loadUserDBFromProperty(String filePath) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			return loadPropertiesHelper(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//helper class
	private UserDatabase<User> loadPropertiesHelper(Properties prop) {
		String DatesStr, TimeStr;
		DatesStr = prop.getProperty("dates");
		TimeStr = prop.getProperty("times");
//		System.out.println(DatesStr);
		String[] timesArray = TimeStr.split(",");
		for (int i = 0; i < timesArray.length; i++) {
			timesArray[i] = timesArray[i].strip();
//					.replaceAll("[_]", ":");
			System.out.println(timesArray[i]);
		}
		String[] datesArray = DatesStr.split(",");
		for (int i = 0; i < datesArray.length; i++) {
//			String temp;
//			temp = datesArray[i].strip();
//			System.out.println(datesArray[i]);
			datesArray[i] = datesArray[i].strip();
			System.out.println(datesArray[i]);
		}
		return null;
	}

	public static void main(String args[]) throws IOException {
		String filePath = "reservation.txt";
		FileIO io = new FileIO();
		io.loadUserDBFromProperty(filePath);

	}

	public static void testLoadFromTextFile() {
		FileIO io = new FileIO();
		UserDatabase<User> usdb = new UserDatabase<User>();
		usdb.addUser("Thomas", new User("Thomas", "Lao"));
		usdb.addUser("Jake", new User("Jake", "Paul"));

//		io.save("reservation.txt", usdb);
//		System.out.println(usdb.getUserDataBase());
//		usdb = io.loadUserDBFromText("reservation.txt");

//		System.out.println();
//		System.out.println(usdb);
	}

}
