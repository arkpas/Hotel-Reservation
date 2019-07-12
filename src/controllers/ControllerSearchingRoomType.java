package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Hotel;
import model.RoomType;

public class ControllerSearchingRoomType implements Initializable {
	
	@FXML private TextField roomTypeIDField;
	@FXML private TextField nameField;
	@FXML private TextField sizeField; 
	@FXML private TextField bedsField;
	@FXML private ComboBox<String> balconyBox;
	@FXML private ComboBox<String> bathroomBox;
	@FXML private TextField priceField;
	
	@FXML private TableView<RoomType> tableView;
	@FXML private TableColumn<RoomType, Integer> roomTypeID;
	@FXML private TableColumn<RoomType, String> name;
	@FXML private TableColumn<RoomType, String> size;
	@FXML private TableColumn<RoomType, String> beds;
	@FXML private TableColumn<RoomType, String> balcony;
	@FXML private TableColumn<RoomType, String> bathroom;
	@FXML private TableColumn<RoomType, Double> price;
				
	
	private Hotel hotel = new Hotel();
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		balconyBox.getItems().addAll("", "yes", "no");
		balconyBox.setValue("");
		bathroomBox.getItems().addAll("", "yes", "no");
		bathroomBox.setValue("");
		

		roomTypeID.setCellValueFactory(new PropertyValueFactory<RoomType, Integer>("roomTypeID"));
		name.setCellValueFactory(new PropertyValueFactory<RoomType, String>("name"));
		size.setCellValueFactory(new PropertyValueFactory<RoomType, String>("size"));
		beds.setCellValueFactory(new PropertyValueFactory<RoomType, String>("beds"));
		balcony.setCellValueFactory(new PropertyValueFactory<RoomType, String>("balcony"));
		bathroom.setCellValueFactory(new PropertyValueFactory<RoomType, String>("bathroom"));
		price.setCellValueFactory(new PropertyValueFactory<RoomType, Double>("pricePerDay"));
	}

	@FXML
	private void searchRoomType () {
		
		tableView.getItems().clear();
		RoomType roomType = RoomType.parseDataForSearch(roomTypeIDField.getText(), nameField.getText(), sizeField.getText(), bedsField.getText(), balconyBox.getValue(), bathroomBox.getValue(), priceField.getText());
		
		List<RoomType> results = new ArrayList<>();
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchRoomType(roomType));
			hotel.disconnectFromDatabase();
		}
		tableView.getItems().setAll(results);
	}

	
	@FXML
	private void searchRoomTypeAll () {
		
		tableView.getItems().clear();
		
		List<RoomType> results = new ArrayList<>();
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchRoomType(null));
			hotel.disconnectFromDatabase();
		}
		
		tableView.getItems().setAll(results);
	}
	

	
}
