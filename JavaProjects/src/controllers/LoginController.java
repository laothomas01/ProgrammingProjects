package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Seat;
import model.SeatDatabase;
import model.ShowDatabase;
import model.User;
import model.UserDatabase;
import util.FileIO;
import views.LogView;

public class LoginController {
	private UserDatabase<User> usdb;
	private ShowDatabase<SeatDatabase<Seat>> shwDB;
	private LogView view;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public LoginController(UserDatabase<User> usdb, ShowDatabase<SeatDatabase<Seat>> shwDB, LogView view) {
		this.usdb = usdb;
		this.shwDB = shwDB;
		this.view = view;
	}

	FileIO io = new FileIO();
	
	/*
	 * I will come back to this. it is very important -Changes the occupancy of
	 * seats
	 * 
	 */
	public void updateShowDatabase() {

	}

	/*
	 * I will come back to this. it is very important. -userdatabase consists of:
	 * user information and user's reservation information -if user wants to remove
	 * reservations -if user wants to add reservations
	 */
	public void updateUserDatabase() {

	}

	public void start() throws IOException {
		LogView.promptMenu();
		/*
		 * Sign[U]p Sign[I]n E[X]it
		 */
		String option = br.readLine();
		if (option.equals("U") || option.equals("u")) {
		} else if (option.equals("I") || option.equals("i")) {

		} else if (option.equals("X") || option.equals("x")) {

		} else {
			System.out.println("Invalid Option!");
			start();
		}

	}

}
