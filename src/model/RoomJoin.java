package model;

public class RoomJoin extends Room {

	private RoomType roomType = new RoomType();
	
	
	public RoomJoin () {
		super();
	}
	
	public RoomJoin (int roomID, int roomTypeID, String name, int size, int beds, int hasBalcony, int hasBathroom, double pricePerDay) {
		this.setRoomID(roomID);
		roomType = new RoomType(roomTypeID, name, size, beds, hasBalcony, hasBathroom, pricePerDay);
		
	}
	
	public int getRoomTypeID () { return roomType.getRoomTypeID(); }
	
	public String getName () { return roomType.getName(); }
	
	public int getSize () { return roomType.getSize(); }
	
	public int getBeds () { return roomType.getBeds(); }
	
	public int getHasBalcony () { return roomType.getHasBalcony(); }
	
	public int getHasBathroom () { return roomType.getHasBathroom(); }
	
	public double getPricePerDay () { return roomType.getPricePerDay(); }
	
	public String getBalcony () {
		return roomType.getBalcony();
	}
	
	public String getBathroom () {
		return roomType.getBathroom();
	}
	
	@Override
	public void setRoomTypeID (int roomTypeID) { roomType.setRoomTypeID(roomTypeID); }
	
	public void setRoomType (String roomType) { this.roomType.setName(roomType); }
	
	public static final RoomJoin parseDataForSearch (String roomID, String roomTypeID, String roomType, String price) {
		
		RoomJoin rj = new RoomJoin();
		
		rj.setRoomID(RoomType.parseInt(roomID));
		rj.setRoomTypeID(RoomType.parseInt(roomTypeID));
		rj.roomType.setName(roomType);
		rj.roomType.setPricePerDay(RoomType.parseDouble(price));
		
		return rj;
	}
	

}
