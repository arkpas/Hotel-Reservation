package model;

public class RoomType {
	
	private int roomTypeID;
	private String name;
	private int size;
	private int beds;
	private int hasBalcony;
	private int hasBathroom;
	private double pricePerDay;
	
	private RoomType() 	{}
	
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
	
	public String getBalcony () {
		return hasBalcony > 0 ? "yes" : "no";
	}
	
	public String getBathroom () {
		return hasBathroom > 0 ? "yes" : "no";
	}
	
	public static RoomType parseData (String name, String size, String beds, String hasBalcony, String hasBathroom, String pricePerDay) {
		RoomType roomType = null;
		
		if (name == null || size == null || beds == null || hasBalcony == null || hasBathroom == null || pricePerDay == null)
			return null;
		if (name.isEmpty() || size.isEmpty() || beds == null || hasBalcony.isEmpty() || hasBathroom.isEmpty() || pricePerDay.isEmpty())
			return null;
		try {
			roomType = new RoomType();
			roomType.name = name;
			roomType.size = Integer.parseInt(size);
			roomType.beds = Integer.parseInt(beds);
			roomType.hasBalcony = hasBalcony.equals("yes") ? 1 : 0;
			roomType.hasBathroom = hasBathroom.equals("yes") ? 1 : 0;
			roomType.pricePerDay = Double.parseDouble(pricePerDay);
			if (roomType.isValid())
				return roomType;
		}
		catch (NumberFormatException e) {return null;}
		return null;
	}
	
	public static RoomType parseDataForSearch (String id, String name, String size, String beds, String hasBalcony, String hasBathroom, String pricePerDay) {
		
		RoomType roomType = new RoomType();
		
		roomType.roomTypeID = RoomType.parseInt(id);
		roomType.name = name;
		roomType.size = RoomType.parseInt(size);
		roomType.beds = RoomType.parseInt(beds);
		roomType.pricePerDay = RoomType.parseDouble(pricePerDay);
		
		if (hasBalcony.isEmpty())
			roomType.hasBalcony = Integer.MAX_VALUE;
		else
			roomType.hasBalcony = hasBalcony.equals("yes") ? 1 : 0;
		
		if (hasBathroom.isEmpty())
			roomType.hasBathroom = Integer.MAX_VALUE;
		else
			roomType.hasBathroom = hasBathroom.equals("yes") ? 1 : 0;
		
		return roomType;
	}
	
	public boolean isEmpty () {
		if (roomTypeID < 0 && name.isEmpty() && size < 0 && beds < 0 && hasBalcony < 0 && hasBathroom < 0 && pricePerDay < 0)
			return true;
		return false;
	}
	
	public boolean isValid () {
		if (name.isEmpty() || size <= 0 || beds < 0 || pricePerDay < 0)
			return false;
		return true;
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
	
	
	@Override
	public String toString () {
		return String.format("ID: %d\t Name: %s\t Size: %d\t Beds: %d\t Balcony: %s\t Bathroom: %s\t\t Price: %.2f/day", roomTypeID, name, size, beds, hasBalcony <=0 ? "no" : "yes", hasBathroom <=0 ? "no" : "yes", pricePerDay);
	}
 	
	
}
