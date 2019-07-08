package model;

public class Client {
	
	private int clientID;
	private String name;
	private String surname;
	private String phoneNumber;
	private String address;
	private String city;
	private String postalCode;
	
	private Client (String name, String surname, String phoneNumber, String address, String city, String postalCode) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public Client (int clientID, String name, String surname, String phoneNumber, String address, String city, String postalCode) {
		this.clientID = clientID;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public int getClientID () { return clientID; }
	
	public String getName() { return name; }
	
	public String getSurname () { return surname; }
	
	public String getPhoneNumber () { return phoneNumber; }
	
	public String getAddress () { return address; }
	
	public String getCity () { return city; }
	
	public String getPostalCode () { return postalCode; }
	
	public static Client createClient (String name, String surname, String phoneNumber, String address, String city, String postalCode) {
		
		if (name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || city.isEmpty() || postalCode.isEmpty())
			return null;
		Client c = new Client(name, surname, phoneNumber, address, city, postalCode);
		return c;
	}
	
	@Override
	public String toString () {
		return String.format("ID: %d\t Name: %-30s Phone: %s\t Address: %s %s %s", clientID, name + " " + surname, phoneNumber, address, postalCode, city);
	}
	
}
