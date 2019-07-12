package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.Hotel;

public class ControllerSearchingClient implements Initializable {
	
	@FXML private TextField clientIDField;
	@FXML private TextField nameField;
	@FXML private TextField surnameField; 
	@FXML private TextField phoneField;
	@FXML private TextField addressField;
	@FXML private TextField cityField;
	@FXML private TextField postalCodeField;
	@FXML private Button searchButton;
	
	@FXML private TableView<Client> tableView;
	@FXML private TableColumn<Client, Integer> clientID;
	@FXML private TableColumn<Client, String> name;
	@FXML private TableColumn<Client, String> surname;
	@FXML private TableColumn<Client, String> phone;
	@FXML private TableColumn<Client, String> address;
	@FXML private TableColumn<Client, String> city;
	@FXML private TableColumn<Client, String> postalCode;
	
	
	
	private Hotel hotel;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		hotel = new Hotel();
		
		clientID.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientID"));
		name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));
		phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));
		address.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
		city.setCellValueFactory(new PropertyValueFactory<Client, String>("city"));
		postalCode.setCellValueFactory(new PropertyValueFactory<Client, String>("postalCode"));
	}
	
	@FXML
	private void searchClient () {
		
		Client client = Client.parseDataForSearch(clientIDField.getText(), nameField.getText(), surnameField.getText(), phoneField.getText(), addressField.getText(), cityField.getText(), postalCodeField.getText());
		
		List<Client> results = new ArrayList<>();
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchClient(client));
			hotel.disconnectFromDatabase();
		}
		
		tableView.getItems().setAll(results);
	}
		
}
