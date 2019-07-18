package model;

import java.time.LocalDate;

public class ReservationJoin {
	
	private Reservation reservation = new Reservation();
	private Client client = new Client();
	private RoomJoin room = new RoomJoin();
	
	private ReservationJoin () {
		super();
	}
	
	public ReservationJoin (int roomID, String roomType) {
		
		this.room.setRoomID(roomID);
		this.room.setRoomType(roomType);
	}
	
	public ReservationJoin (int reservationID, int clientID, String name, String surname, int roomID, LocalDate dateFrom, LocalDate dateTo) {
		reservation.setReservationID(reservationID);
		client.setClientID(clientID);
		client.setName(name);
		client.setSurname(surname);
		room.setRoomID(roomID);
		reservation.setDateFrom(dateFrom);
		reservation.setDateTo(dateTo);
	}
	
	public static final ReservationJoin parseReservationSearch (String reservationID, String clientID, String name, String surname, String roomID) {
		ReservationJoin rj = new ReservationJoin();
		rj.reservation.setReservationID(ReservationJoin.parseInt(reservationID));
		rj.client.setClientID(ReservationJoin.parseInt(reservationID));
		rj.client.setName(name);
		rj.client.setSurname(surname);
		rj.room.setRoomID(ReservationJoin.parseInt(roomID));
		
		return rj;
	}
	
	public static final ReservationJoin parseFreeRoomSearch (LocalDate dateFrom, LocalDate dateTo) {
		ReservationJoin rj = new ReservationJoin();
		
		if (dateFrom == null || dateTo == null)
			return null;
		rj.reservation.setDateFrom(dateFrom);
		rj.reservation.setDateTo(dateTo);
		
		return rj;
	}
	
	private static final int parseInt (String text) {
		int result = Integer.MAX_VALUE;
		
		if (!text.isEmpty()) {
			try {
				result = Integer.parseInt(text);
			}
			catch (NumberFormatException e) {result = Integer.MIN_VALUE;}
		}
		
		return result;
	}
	
	public int getReservationID () { return reservation.getReservationID(); }
	
	public int getClientID () { return client.getClientID(); }
	
	public String getClientName () { return client.getName(); }
	
	public String getClientSurname () { return client.getSurname(); }
	
	public int getRoomID () { return room.getRoomID(); }
	
	public String getRoomType () { return room.getName(); }
	
	public LocalDate getDateFrom () { return reservation.getDateFrom(); }
	
	public LocalDate getDateTo () { return reservation.getDateTo(); }

}
