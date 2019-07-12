package model;

public class RoomJoinRoomType {
	private int roomID;
	private int roomTypeID;
	private String name;
	private int size;
	private int beds;
	private int hasBalcony;
	private int hasBathroom;
	private double pricePerDay;
	
	
	private RoomJoinRoomType () {}
	
	public RoomJoinRoomType (int roomID, int roomTypeID, String name, int size, int beds, int hasBalcony, int hasBathroom, double pricePerDay) {
		this.roomID = roomID;
		this.roomTypeID = roomTypeID;
		this.name = name.toLowerCase();
		this.size = size;
		this.beds = beds;
		this.hasBalcony = hasBalcony;
		this.hasBathroom = hasBathroom;
		this.pricePerDay = pricePerDay;
	}
	
	public int getRoomID () { return roomID; }
	
	public int getRoomTypeID () { return roomTypeID; }
	
	public String getName () { return name; }
	
	public int getSize () { return size; }
	
	public int getBeds () { return beds; }
	
	public int getHasBalcony () { return hasBalcony; }
	
	public int getHasBathroom () { return hasBathroom; }
	
	public double getPricePerDay () { return pricePerDay; }
	
	public String getBalcony () {
		return hasBalcony > 0 ? "yes" : "no";
	}
	
	public String getBathroom () {
		return hasBathroom > 0 ? "yes" : "no";
	}
	
	public static RoomJoinRoomType parseDataForSearch (String roomID, String roomTypeID, String roomType, String price) {
		
		RoomJoinRoomType rjtr = new RoomJoinRoomType();
		
		rjtr.roomID = RoomJoinRoomType.parseInt(roomID);
		rjtr.roomTypeID = RoomJoinRoomType.parseInt(roomTypeID);
		rjtr.name = roomType;
		rjtr.pricePerDay = RoomJoinRoomType.parseDouble(price);
		
		return rjtr;
	}
	
	private static int parseInt (String number) {
		int result = Integer.MAX_VALUE;
		if (!number.isEmpty()) {
			try {
				result = Integer.parseInt(number);
			}
			catch (NumberFormatException e) {result = Integer.MIN_VALUE;}
		}
		return result;
	}
	
	private static double parseDouble (String number) {
		double result = Double.MAX_VALUE;
		if (!number.isEmpty()) {
			try {
				result = Double.parseDouble(number);
			}
			catch (NumberFormatException e) {result = Double.MIN_VALUE;}
		}
		return result;
	}

}
