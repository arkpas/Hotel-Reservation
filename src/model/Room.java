package model;

public class Room {
	
	private int roomID;
	private int roomTypeID;
	
	public Room () {}
	
	public Room (int roomID, int roomTypeID) {
		this.roomID = roomID;
		this.roomTypeID = roomTypeID;
	}
	
	public int getRoomID () { return roomID; }
	
	public int getRoomTypeID () { return roomTypeID; }
	
	
	public void setRoomID (int roomID) { this.roomID = roomID; }
	
	public void setRoomTypeID (int roomTypeID) { this.roomTypeID = roomTypeID; }
	
	
	@Override
	public String toString () {
		return String.format("ID: %d\t Room Type: %d", roomID, roomTypeID);
	}
	
	
}
