package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class StartMenu {
	

//	private boolean finish = false;
//	// in-Memory datastructre
////	HashMap<String, User> userDataBase = new HashMap<String, User>();
//	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	private String key = "";
//	private String pssWord = "";
//	private boolean startTransaction = true;
//
//	// put into another class later
//	public void run() throws IOException {
//		initialMenu();
//	}

	/*
	 * PART 1: CREATING YOUR ACCOUNT
	 */
	// user interface for creating an account
//	public void initialMenu() throws IOException {
//		while (finish == false) {
//			System.out.println("Sign[U]p\nSign[I]n\n[D]isplayInfo\nE[X]it\n");
//			String choice = reader.readLine();
//			if (choice.equals("x") || choice.equals("X")) {
//				System.out.println("Exiting!");
//				finish = true;
//				Exit();
//			} else if (choice.equals("U") || choice.equals("u")) {
//				signUp();
//			} else if (choice.equals("I") || choice.equals("i")) {
////				signIn();
//				// need to break out of the loop or menu prompt repeats
//				finish = true;
//
//			} else if (choice.equals("D") || choice.equals("d")) {
//				displaySignUps();
//			} else {
//				System.out.println("Invalid Input!");
//			}
//
//		}
//	}
//
//	public void displaySignUps() throws IOException {
//		System.out.println(userDataBase.toString());
//		initialMenu();
//	}
//
	public void signUp()  {
		
//		System.out.println("Enter a user name:\n");
//		String userName = reader.readLine();
//		System.out.println("Enter a password:\n");
//		String password = reader.readLine();
//		if (userDataBase.containsKey(userName)) {
//			System.out.println("Username already taken");
//			signUp();
//		} else {
//			User account = new User(userName, password);
//			userDataBase.put(userName, account);
//			System.out.println("Thank you for signing up!");
//			initialMenu();
//		}
	}

//	public void signIn() throws IOException {
//		boolean finished = false;
//		String username = "";
//		String password = "";
//		while (finished == false) {
//			System.out.println("[L]ogin\nE[X]it\n");
//			String choice = reader.readLine();
//			if (choice.equals("L") || choice.equals("l")) {
//				System.out.printf("Login: ");
//				username = reader.readLine();
//				System.out.printf("Password: ");
//				password = reader.readLine();
//				finished = true;
//				for (Map.Entry<String, User> entry : userDataBase.entrySet()) {
//					key = entry.getKey();
//					pssWord = entry.getValue().getPassword();
//				}
//				if (key.equals(username) && pssWord.equals(password)) {
//					System.out.println("You have logged in!");
//					finished = true;
////					beginTransaction();
//				} else if (key.equals(username) && !(pssWord.equals(password))) {
//					System.out.println("Invalid password!");
//					finished = false;
//					signIn();
//				} else if (!(key.equals(username) && pssWord.equals(password))) {
//					System.out.println("Invalid username!");
//					finished = false;
//					signIn();
//				}
//			} else if (choice.equals("X") || choice.equals("x")) {
//				System.out.println("Are you sure? Y/N");
//				String confirm = reader.readLine();
//				if (confirm.equals("Y") || confirm.equals("y")) {
//					finished = true;
//					initialMenu();
//				} else if (confirm.equals("N") || confirm.equals("n")) {
//					signIn();
//				}
//			}
//
//		}
//
//	}

//	public void Exit() throws IOException {
//		File reserve;
//		System.out.println("Confirm Exit: Y/N");
//		String choice = reader.readLine();
//		if (choice.equals("Y") || choice.equals("y")) {
//			// write to the file if exit happens
//			// check if file is empty
//			// persist the data
//			reserve = new File("reservation.txt");
//			if (reserve.createNewFile()) {
//				System.out.println("File created: " + reserve.getName());
//			} else {
//				System.out.println("File already exists.");
//			}
//
//			System.out.println("GoodBye!");
//			System.exit(1);
//		} else if (choice.equals("N") || choice.equals("n")) {
//			finish = false;
//			this.initialMenu();
//		}
//
//	}
	// PART 2: USER BEGINS MAKING RESERVATIONS

//	public void beginTransaction() throws IOException {
//		System.out.println("Transaction begins!");
//		while (startTransaction == true) {
//			System.out.println("[R]eserve\n[V]iew\n[C]ancel\n[O]ut\n");
//			String choice = reader.readLine();
//			if(choice.equals("R") || choice.equals("r"))
//			{
//				Reserve();
//			}
//			else if(choice.equals("V"))
//		}
//
//	}

//	public void Reserve() {
//
//	}
//
//	public void View() {
//
//	}
//
//	public void Cancel() {
//
//	}
//
//	public void Out() {
//
//	}
//
	public static void main(String args[]) throws IOException {
		StartMenu s = new StartMenu();
//		s.signUp();
//		s.run();
	}
}
