package model;

public class Client {
	
	private int clientID;
	private String name;
	private String surname;
	private String phoneNumber;
	private String address;
	private String city;
	private String postalCode;
	
	
	public Client() {}

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
	
	
	public void setClientID (int clientID) { this.clientID = clientID; }
	
	public void setName (String name) { this.name = name; }
	
	public void setSurname (String surname) { this.surname = surname; }
	
	public void setPhoneNumber (String phoneNumber) { this.phoneNumber = phoneNumber; }
	
	public void setAddress (String address) { this.address = address; }
	
	public void setCity (String city) { this.city = city; }
	
	public void setPostalCode (String postalCode) { this.postalCode = postalCode; }
	
	
	public static Client parseData (String name, String surname, String phoneNumber, String address, String city, String postalCode) {
		
		if (name == null || surname == null || phoneNumber == null || address == null || city == null || postalCode == null)
			return null; 
		if (name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || city.isEmpty() || postalCode.isEmpty())
			return null; 
		
		Client client = new Client();
		client.clientID = Integer.MAX_VALUE;
		client.name = name;
		client.surname = surname;
		client.phoneNumber = phoneNumber;
		client.address = address;
		client.city = city;
		client.postalCode = postalCode;
		
		return client;
	}

	public static Client parseDataForSearch (String clientID, String name, String surname, String phoneNumber, String address, String city, String postalCode) {
		Client client = new Client();
		
		client.clientID = Integer.MAX_VALUE;
		if (!clientID.isEmpty()) {
			try {
				client.clientID = Integer.parseInt(clientID);
			}
			catch (NumberFormatException e) {client.clientID = Integer.MIN_VALUE; }
		}
		
		client.name = name;
		client.surname = surname;
		client.phoneNumber = phoneNumber;
		client.address = address;
		client.city = city;
		client.postalCode = postalCode;
		
		return client;
	}
	
	public boolean isEmpty () {
		if (clientID == 0 && name.isEmpty() && surname.isEmpty() && phoneNumber.isEmpty() && address.isEmpty() && city.isEmpty() && postalCode.isEmpty())
			return true;
		return false;
	}
	
	@Override
	public String toString () {
		return String.format("ID: %d\t Name: %-30s Phone: %s\t Address: %s %s %s", clientID, name + " " + surname, phoneNumber, address, postalCode, city);
	}
	
}
