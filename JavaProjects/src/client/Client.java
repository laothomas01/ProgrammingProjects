package client;

import model.Seat;
import model.SeatDatabase;
import model.ShowDatabase;
import model.User;
import model.UserDatabase;
import util.FileConstants;
import util.FileIO;

public class Client implements FileConstants {
	// attributes
	private UserDatabase<User> usDB;
	private ShowDatabase<SeatDatabase<Seat>> shwDB;
	FileIO io;

	Client() {
		usDB = null;
		shwDB = null;
		io = new FileIO();
	}
}
