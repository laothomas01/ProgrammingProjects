import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class StartMenu {
	private boolean finish = false;
	// in-Memory datastructre
	HashMap<String, User> signUpInfo = new HashMap<String, User>();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private String key = "";
	private String pssWord = "";
	private boolean startTransaction = true;

	public void run() throws IOException {
		initialMenu();
	}

	public void initialMenu() throws IOException {
		while (finish == false) {
			System.out.println("Sign[U]p\nSign[I]n\n[D]isplayInfo\nE[X]it\n");
			String choice = reader.readLine();
			if (choice.equals("x") || choice.equals("X")) {
				System.out.println("Exiting!");
				finish = true;
				Exit();
			} else if (choice.equals("U") || choice.equals("u")) {
				signUp();
			} else if (choice.equals("I") || choice.equals("i")) {
				signIn();
				// need to break out of the loop or menu prompt repeats
				finish = true;
			} else if (choice.equals("D") || choice.equals("d")) {
				displaySignUps();
			} else {
				System.out.println("Invalid Input!");
			}

		}
	}

	public void displaySignUps() throws IOException {
		System.out.println(signUpInfo.toString());
		initialMenu();
	}

	public void signUp() throws IOException {
		System.out.println("Enter a user name:\n");
		String userName = reader.readLine();
		System.out.println("Enter a password:\n");
		String password = reader.readLine();
		if (signUpInfo.containsKey(userName)) {
			System.out.println("Username already taken");
			signUp();
		} else {
			User account = new User(userName, password);
			signUpInfo.put(userName, account);
			System.out.println("Thank you for signing up!");
			initialMenu();
		}
	}

	public void signIn() throws IOException {
		System.out.printf("Login: ");
		String userName = reader.readLine();
		System.out.printf("Password: ");
		String password = reader.readLine();
		System.out.println("E[X]it ");
		for (Map.Entry<String, User> entry : signUpInfo.entrySet()) {
			key = entry.getKey();
			pssWord = entry.getValue().getPassword();
		}
		if (key.equals(userName) && pssWord.equals(password)) {
			System.out.println("You have logged in!");
//			beginTransaction();
		} else if (key.equals(userName) && !(pssWord.equals(password))) {
			System.out.println("Invalid password!");
			signIn();
		} else if (!(key.equals(userName) && pssWord.equals(password))) {
			System.out.println("Invalid username!");
			signIn();
		}

	}

//	public void beginTransaction() {
//		System.out.println("Transaction begins!");
//		while (startTransaction == true) {
//				
//		}
//
//	}

	public void Exit() throws IOException {
		File reserve;
		System.out.println("Confirm Exit: Y/N");
		String choice = reader.readLine();
		if (choice.equals("Y") || choice.equals("y")) {
			// write to the file if exit happens
			// check if file is empty
			// persist the data
//			reserve = new File("reservation.txt");
//			if (reserve.createNewFile()) {
//				System.out.println("File created: " + reserve.getName());
//			}
//			else
//			{
//				System.out.println("File already exists.");
//			}

			System.out.println("GoodBye!");
			System.exit(1);
		} else if (choice.equals("N") || choice.equals("n")) {
			finish = false;
			this.initialMenu();
		}

	}

	public static void main(String args[]) throws IOException {
		StartMenu s = new StartMenu();
		s.run();
	}
}
