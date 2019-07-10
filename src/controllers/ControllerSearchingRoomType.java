package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Hotel;
import model.RoomType;

public class ControllerSearchingRoomType {
	
	@FXML private TextField roomTypeIDField;
	@FXML private TextField nameField;
	@FXML private TextField sizeField; 
	@FXML private TextField bedsField;
	@FXML private RadioButton balconyButton;
	@FXML private RadioButton bathroomButton;
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

	@FXML
	private void searchRoomType () {
		
		int idParsed = 0;
		int bedsParsed = 0;
		int sizeParsed = 0;
		double priceParsed = 0;
		
		if (!roomTypeIDField.getText().isEmpty()) {
			try {
				idParsed = Integer.parseInt(roomTypeIDField.getText());
			}
			catch (NumberFormatException e) {} //intentionally empty, if field holds non number data, we just do not pass it further
		}
		
		if (!bedsField.getText().isEmpty()) {
			try {
				bedsParsed = Integer.parseInt(bedsField.getText());
			}
			catch (NumberFormatException e) {} //intentionally empty, if field holds non number data, we just do not pass it further
		}
		
		if (!sizeField.getText().isEmpty()) {
			try {
				sizeParsed = Integer.parseInt(sizeField.getText());
			}
			catch (NumberFormatException e) {} //intentionally empty, if field holds non number data, we just do not pass it further
		}
		
		if (!priceField.getText().isEmpty()) {
			try {
				priceParsed = Double.parseDouble(priceField.getText());
			}
			catch (NumberFormatException e) {} //intentionally empty, if field holds non number data, we just do not pass it further
		}
		
		RoomType roomType = new RoomType(idParsed, nameField.getText(), sizeParsed, bedsParsed, balconyButton.isSelected(), bathroomButton.isSelected(), priceParsed);
		List<RoomType> results = new ArrayList<>();
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchRoomType(roomType));
			hotel.disconnectFromDatabase();
		}

		roomTypeID.setCellValueFactory(new PropertyValueFactory<RoomType, Integer>("roomTypeID"));
		name.setCellValueFactory(new PropertyValueFactory<RoomType, String>("name"));
		size.setCellValueFactory(new PropertyValueFactory<RoomType, String>("size"));
		beds.setCellValueFactory(new PropertyValueFactory<RoomType, String>("beds"));
		balcony.setCellValueFactory(new PropertyValueFactory<RoomType, String>("balcony"));
		bathroom.setCellValueFactory(new PropertyValueFactory<RoomType, String>("bathroom"));
		price.setCellValueFactory(new PropertyValueFactory<RoomType, Double>("pricePerDay"));
		
		tableView.getItems().setAll(results);
	}
	
	@FXML
	private void searchRoomTypeAll () {
		List<RoomType> results = new ArrayList<>();
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchRoomType(null));
			hotel.disconnectFromDatabase();
		}

		roomTypeID.setCellValueFactory(new PropertyValueFactory<RoomType, Integer>("roomTypeID"));
		name.setCellValueFactory(new PropertyValueFactory<RoomType, String>("name"));
		size.setCellValueFactory(new PropertyValueFactory<RoomType, String>("size"));
		beds.setCellValueFactory(new PropertyValueFactory<RoomType, String>("beds"));
		balcony.setCellValueFactory(new PropertyValueFactory<RoomType, String>("balcony"));
		bathroom.setCellValueFactory(new PropertyValueFactory<RoomType, String>("bathroom"));
		price.setCellValueFactory(new PropertyValueFactory<RoomType, Double>("pricePerDay"));
		
		tableView.getItems().setAll(results);
	}
	
}
