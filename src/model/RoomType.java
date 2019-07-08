package model;

public class RoomType {
	
	private int roomTypeID;
	private String name;
	private int size;
	private int beds;
	private int hasBalcony;
	private int hasBathroom;
	private double pricePerDay;
	
	private RoomType (String name, int size, int beds, boolean hasBalcony, boolean hasBathroom, double pricePerDay) {
		this.name = name.toLowerCase();
		this.size = size;
		this.beds = beds;
		this.hasBalcony = hasBalcony ? 1 : 0;
		this.hasBathroom = hasBathroom ? 1 : 0;
		this.pricePerDay = pricePerDay;
	}
	
	public RoomType (int roomTypeID, String name, int size, int beds, int hasBalcony, int hasBathroom, double pricePerDay) {
		this.roomTypeID = roomTypeID;
		this.name = name;
		this.size = size;
		this.beds = beds;
		this.hasBalcony = hasBalcony;
		this.hasBathroom = hasBathroom;
		this.pricePerDay = pricePerDay;
	}
	
	public int getRoomTypeID () { return roomTypeID; }
	
	public String getName () { return name; }
	
	public int getSize () { return size; }
	
	public int getBeds () { return beds; }
	
	public int getHasBalcony () { return hasBalcony; }
	
	public int getHasBathroom () { return hasBathroom; }
	
	public double getPricePerDay () { return pricePerDay; }
	
	public static RoomType createRoomType (String name, int size, int beds, boolean hasBalcony, boolean hasBathroom, double pricePerDay) {
		
		if (name.isEmpty())
			return null;
		RoomType rt = new RoomType(name, size, beds, hasBalcony, hasBathroom, pricePerDay);
		return rt;
		
	}
	
	@Override
	public String toString () {
		return String.format("ID: %d\t Name: %s\t Size: %d\t Beds: %d\t Balcony: %s\t Bathroom: %s\t\t Price: %.2f/day", roomTypeID, name, size, beds, hasBalcony <=0 ? "no" : "yes", hasBathroom <=0 ? "no" : "yes", pricePerDay);
	}
 	
	
}
