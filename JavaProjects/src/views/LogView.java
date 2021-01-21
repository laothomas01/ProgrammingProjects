package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogView {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// displaying a prompt for the user
	public static void promptMenu() {
		System.out.println("Sign[U]p\nSign[I]n\nE[X]it\n");
	}

	// different options the user can choose from
	public static void Login() throws IOException {
		enterUserName();
		
	}

	// different options the user can choose from
	public static void Signup() {
	}

	// different options the user can choose from. should a user make an option
	// error, user can exit
	public static void Exit() {

	}

	// actions the user can do
	public static String enterUserName() throws IOException {
		System.out.println("Enter a username:");
		String username = br.readLine();
		return username;
	}

	// actions the user can do
	public static String enterPassword() throws IOException {
		System.out.println("Enter a password:");
		String password = br.readLine();
		return password;
	}
	
	//display customer reservations
	
	//display showDatabase seats
	
	//
	
	

}
