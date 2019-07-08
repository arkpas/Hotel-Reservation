package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class Hotel {
	private Connection connection = null;
	
	public boolean connectToDatabase () {
	
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:database/hotel.db");
			createTables();
			}
		catch (SQLException e) {System.err.println(e.getMessage()); return false; }
		
		return true;
	}
	
	public void disconnectFromDatabase () {
		try {
		connection.close();
		}
		catch (SQLException e) {System.err.println(e.getMessage());}
	}
	
	private boolean createTables () {
		
		String query = "";
		
		try {
			Statement statement = connection.createStatement();
			statement.execute("PRAGMA foreign_keys = ON;");
			
			query = "CREATE TABLE IF NOT EXISTS Clients(ClientID integer NOT NULL PRIMARY KEY, Name text NOT NULL, Surname text NOT NULL, PhoneNumber text NOT NULL, Address text NOT NULL, City text NOT NULL, PostalCode text);";
			statement.execute(query);
			query = "CREATE TABLE IF NOT EXISTS RoomTypes(RoomTypeID integer NOT NULL PRIMARY KEY, Name text NOT NULL, Size integer NOT NULL, Beds integer NOT NULL, HasBalcony integer NOT NULL, HasBathroom integer NOT NULL, PricePerDay integer NOT NULL);";
			statement.execute(query);
			query = "CREATE TABLE IF NOT EXISTS Rooms(RoomID integer NOT NULL PRIMARY KEY, RoomTypeID integer NOT NULL, FOREIGN KEY (RoomTypeID) REFERENCES RoomTypes (RoomTypeID));";
			statement.execute(query);
			query = "CREATE TABLE IF NOT EXISTS Reservations(ReservationID integer NOT NULL PRIMARY KEY, ClientID integer NOT NULL, RoomID integer NOT NULL, DateFrom text NOT NULL, DateTo text NOT NULL, Price integer NOT NULL, FOREIGN KEY (ClientID) REFERENCES Clients(ClientID), FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID));";
			statement.execute(query);
			
			return true;
			
		}
		catch (SQLException e) {System.err.println(e.getMessage());}
		
		return false;
	}
	
	public String addClient (Client client) {
		
		/* This method returns null only when client is added successfully 
		 * Otherwise it will return reason why adding client failed */
		
		if (client == null)
			return "No data provided";
		
		try {
		PreparedStatement statement	= connection.prepareStatement("SELECT * FROM Clients WHERE Name=? AND Surname=? AND PhoneNumber=?;");
		statement.setString(1, client.getName());
		statement.setString(2, client.getSurname());
		statement.setString(3, client.getPhoneNumber());
		if (statement.executeQuery().isBeforeFirst()) {
			return "Client already exists in database";
		}
			
		statement = connection.prepareStatement("INSERT INTO Clients VALUES (NULL, ?, ?, ?, ?, ?, ?);");
		statement.setString(1, client.getName());
		statement.setString(2, client.getSurname());
		statement.setString(3, client.getPhoneNumber());
		statement.setString(4, client.getAddress());
		statement.setString(5, client.getCity());
		statement.setString(6, client.getPostalCode());
		return null;
		}
		
		catch (SQLException e) { System.out.println(e.getMessage()); return "Database access error"; }
		
	}
	
	public String addRoom (Room room) {
		
		/* This method returns null only when room is added successfully 
		 * Otherwise it will return reason why adding room failed */
		
		if (room == null)
			return "No data delivered";
		
		try {
			
		PreparedStatement statement	= connection.prepareStatement("SELECT * FROM Rooms WHERE RoomID=?;");
		statement.setInt(1, room.getRoomID());
		if (statement.executeQuery().isBeforeFirst()) {
			return "Room already exists";
		}
		
		statement = connection.prepareStatement("SELECT * FROM RoomTypes WHERE RoomTypeID=?;");
		statement.setInt(1, room.getRoomTypeID());
		if (!statement.executeQuery().isBeforeFirst()) {
			return "No room type with ID: " + room.getRoomTypeID();
		}
			
		statement = connection.prepareStatement("INSERT INTO Rooms VALUES (?, ?);");
		statement.setInt(1, room.getRoomID());
		statement.setInt(2, room.getRoomTypeID());
		statement.execute();
		return null;
		

		}
		
		catch (SQLException e) { System.err.println(e.getMessage()); return "Database access error"; }
		
	}
	
	public String addRoomType (RoomType roomType) {
		
		if (roomType == null)
			return "No data provided";
		
		try {
		PreparedStatement statement	= connection.prepareStatement("SELECT * FROM RoomTypes WHERE Name=?;");
		statement.setString(1, roomType.getName());
		if (statement.executeQuery().isBeforeFirst()) {
			return "Room type already exists";
		}
		
		statement = connection.prepareStatement("INSERT INTO RoomTypes(Name, Size, Beds, HasBalcony, HasBathroom, PricePerDay) VALUES (?, ?, ?, ?, ?, ?);");
		statement.setString (1, roomType.getName());
		statement.setInt (2, roomType.getSize());
		statement.setInt (3, roomType.getBeds());
		statement.setInt (4, roomType.getHasBalcony());
		statement.setInt (5, roomType.getHasBathroom());
		statement.setDouble (6, roomType.getPricePerDay());
		statement.execute();
		return null;
		}
		
		catch (SQLException e) { System.err.println(e.getMessage()); return "Database access error"; }
		
	}
	
	public String addReservation (Reservation reservation) {
		if (reservation == null)
			return "No data provided";
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Clients WHERE ClientID=?;");
			statement.setInt(1, reservation.getClientID());
			ResultSet result = statement.executeQuery();
			if (!result.isBeforeFirst()) {
				return "No client with ID: " + reservation.getClientID();
			}
			
			statement = connection.prepareStatement("SELECT RoomTypeID FROM Rooms WHERE RoomID=?;");
			statement.setInt(1, reservation.getRoomID());
			result = statement.executeQuery();
			if (!result.isBeforeFirst()) {
				return "No room with ID: " + reservation.getRoomID();
			}
			result.next();
			int roomTypeID = result.getInt(1);
			
			statement = connection.prepareStatement("SELECT * FROM Reservations WHERE RoomID=? AND DateFrom <= ? AND DateTo >= ?;");
			statement.setInt(1, reservation.getRoomID());
			statement.setString(2, reservation.getDateTo().toString());
			statement.setString(3, reservation.getDateFrom().toString());
			result = statement.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				return "Date already reserved in ReservationID: " + result.getInt(1);
			}
			
			statement = connection.prepareStatement("SELECT PricePerDay FROM RoomTypes WHERE RoomTypeID=?;");
			statement.setInt(1, roomTypeID);
			result = statement.executeQuery();
			result.next();
			int pricePerDay = result.getInt(1);
			int days = Period.between(reservation.getDateFrom(), reservation.getDateTo()).getDays();
			
			reservation.setPrice(days*pricePerDay);
			
			statement = connection.prepareStatement("INSERT INTO Reservations VALUES (NULL, ?, ?, ?, ?, ?)");
			statement.setInt(1, reservation.getClientID());
			statement.setInt(2, reservation.getRoomID());
			statement.setString(3, reservation.getDateFrom().toString());
			statement.setString(4, reservation.getDateTo().toString());
			statement.setDouble(5, reservation.getPrice());
			
			statement.execute();
			
			return null;
			
		}
		catch (SQLException e) { e.printStackTrace(); return "Database access error"; }
		
	}
	
	public List<Client> getClients () {
		List<Client> clients = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Clients");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int clientID = result.getInt(1);
				String name = result.getString(2);
				String surname = result.getString(3);
				String phoneNumber = result.getString(4);
				String address = result.getString(5);
				String city = result.getString(6);
				String postalCode = result.getString(7);
				
				clients.add(new Client(clientID, name, surname, phoneNumber, address, city, postalCode));
			}
		}
		catch (SQLException e) {System.out.println(e.getMessage());}
		
		return clients;
	}
	
	public List<Reservation> getReservations () {
		List<Reservation> reservations = new ArrayList<>();
		try {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reservations;");
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			int reservationID = result.getInt(1);
			int clientID = result.getInt(2);
			int roomID = result.getInt(3);
			LocalDate dateFrom = LocalDate.parse(result.getString(4));
			LocalDate dateTo = LocalDate.parse(result.getString(5));
			double price = result.getFloat(6);
			
			
			reservations.add(new Reservation(reservationID, clientID, roomID, dateFrom, dateTo, price));
			
		}
		}
		catch (SQLException e) {System.out.println(e.getMessage());}
		
		return reservations;
	}
	
	public List<Room> getRooms () {
		List<Room> rooms = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rooms;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int roomID = result.getInt(1);
				int roomTypeID = result.getInt(2);
				
				rooms.add(new Room(roomID, roomTypeID));
				
			}
		}
		catch (SQLException e) {System.out.println(e.getMessage());}
		
		return rooms;
	}
	
	public List<RoomType> getRoomTypes () {
		List<RoomType> roomTypes = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM RoomTypes");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int roomTypeID = result.getInt(1);
				String name = result.getString(2);
				int size = result.getInt(3);
				int beds = result.getInt(4);
				int hasBalcony = result.getInt(5);
				int hasBathroom = result.getInt(6);
				double pricePerDay = result.getDouble(7);
				
				roomTypes.add(new RoomType(roomTypeID, name, size, beds, hasBalcony, hasBathroom, pricePerDay));
			}
		}
		catch (SQLException e) {System.out.println(e.getMessage());}
		
		return roomTypes;
	}
	
	
	public boolean executeQuery (String query) {
		
		try {
			Statement statement = connection.createStatement();
			statement.execute(query);
			return true;
		}
		catch (SQLException e) {System.err.println(e.getMessage());  return false; }
		
	}
	
	
	
	

}
