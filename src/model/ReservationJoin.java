package model;

import java.time.LocalDate;

public class ReservationJoin {
	
	private Reservation reservation = new Reservation();
	private Client client = new Client();
	private Room room = new Room();
	
	public ReservationJoin (int reservationID, int clientID, int roomID, LocalDate dateFrom, LocalDate dateTo) {
		reservation.setReservationID(reservationID);
		client.setClientID(clientID);
		room.setRoomID(roomID);
		reservation.setDateFrom(dateFrom);
		reservation.setDateTo(dateTo);
	}
	
	
	public int getReservationID () { return reservation.getReservationID(); }
	
	public int getClientID () { return client.getClientID(); }
	
	public int getRoomID () { return room.getRoomID(); }
	
	public LocalDate getDateFrom () { return reservation.getDateFrom(); }
	
	public LocalDate getDateTo () { return reservation.getDateTo(); }

}
