package model;

public class Seat {
	private double seatPrice;
	private int seatNumber;
	private boolean isOccupied;
	private String seatSection;
	private String date;
	private String time;

	// Constructors

	public Seat(String seatSection, int seatNumber, double seatPrice, boolean isOccupied, String date, String time) {
		this.seatPrice = seatPrice;
		this.seatNumber = seatNumber;
		this.isOccupied = isOccupied;
		this.seatSection = seatSection;
		this.date = date;
		this.time = time;
	}

	public Seat(double seatPrice, int seatNumber, String seatSection) {
		this(seatSection, seatNumber, seatPrice, false, "", "");
	}

	public Seat() {
		this("", -1, -1, false, "", "");
	}

	// GETTERS,SETTERS
	public String getSeatSection() {
		return this.seatSection;
	}

	public String getSeatID() {
		return this.seatSection + this.seatNumber;
	}

	public double getSeatPrice() {
		return this.seatPrice;
	}

	public int getSeatNumber() {
		return this.seatNumber;
	}

	public String getSeatDate() {
		return this.date;
	}

	public String getSeatTime() {
		return this.time;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setSeatSection(String seatSection) {
		this.seatSection = seatSection;
	}

	public void setSeatPrice(double seatPrice) {
		this.seatPrice = seatPrice;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public String toString() {
		return String.format("seatPrice = $%.2f\t\nseatNumber=%d\t\nseatSection=%s\t\nseatDate=%s\t\nseatTime=%s\n\n",
				this.seatPrice, this.seatNumber, this.seatSection, this.date, this.time);
	}

}
