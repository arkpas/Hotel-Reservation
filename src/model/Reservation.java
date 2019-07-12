package model;

import java.time.LocalDate;

public class Reservation {
	private int reservationID;
	private int clientID;
	private int roomID;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private double price;
	
	public Reservation () {}
	
	private Reservation (int clientID, int roomID, LocalDate dateFrom, LocalDate dateTo) {
		this.clientID = clientID;
		this.roomID = roomID;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		
	} 
	
	public Reservation (int reservationID, int clientID, int roomID, LocalDate dateFrom, LocalDate dateTo, double price) {
		this.reservationID = reservationID;
		this.clientID = clientID;
		this.roomID = roomID;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
	} 
	
	public int getReservationID () { return reservationID; }
	
	public int getClientID () { return clientID; }
	
	public int getRoomID () { return roomID; }
	
	public LocalDate getDateFrom () { return dateFrom; }
	
	public LocalDate getDateTo () { return dateTo; }
	
	public double getPrice () { return price; }
	
	
	public void setReservationID (int reservationID) { this.reservationID = reservationID;}
	
	public void setClientID (int clientID) { this.clientID = clientID; }
	
	public void setRoomID (int roomID) { this.roomID = roomID; }
	
	public void setDateFrom (LocalDate dateFrom) { this.dateFrom = dateFrom; }
	
	public void setDateTo (LocalDate dateTo) { this.dateTo = dateTo; }
	
	public void setPrice (double price) { this.price = price; }

	
	public static Reservation createReservation (int clientID, int roomID, LocalDate dateFrom, LocalDate dateTo) {
		
		if (dateFrom == null || dateTo == null)
			return null;
		Reservation r = new Reservation(clientID, roomID, dateFrom, dateTo);
		return r;
	}
	
	@Override
	public String toString () {
		return String.format("ID: %d\t ClientID: %d\t Room: %s\t From: %s\t To: %s\t Price: %.2f", reservationID, clientID, roomID, dateFrom.toString(), dateTo.toString(), price);
	}
}
