package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Hotel;
import model.RoomJoin;

public class ControllerSearchingRoom implements Initializable {
	
	@FXML private TextField roomIDField;
	@FXML private TextField roomTypeIDField;
	@FXML private TextField roomTypeField;
	@FXML private TextField priceField;
	
	private Hotel hotel = new Hotel();
	
	@FXML private TableView<RoomJoin> tableView;
	@FXML private TableColumn<RoomJoin, Integer> roomIDCol;
	@FXML private TableColumn<RoomJoin, Integer> roomTypeIDCol;
	@FXML private TableColumn<RoomJoin, String> nameCol;
	@FXML private TableColumn<RoomJoin, Double> priceCol;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		roomIDCol.setCellValueFactory(new PropertyValueFactory<RoomJoin, Integer>("roomID"));
		roomTypeIDCol.setCellValueFactory(new PropertyValueFactory<RoomJoin, Integer>("roomTypeID"));
		nameCol.setCellValueFactory(new PropertyValueFactory<RoomJoin, String>("name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<RoomJoin, Double>("pricePerDay"));
	}
	
	@FXML
	private void searchRoom () {
		
		List<RoomJoin> results = new ArrayList<>();
		
		RoomJoin rj = RoomJoin.parseDataForSearch(roomIDField.getText(), roomTypeIDField.getText(), roomTypeField.getText(), priceField.getText());
		
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchRoom(rj));
			hotel.disconnectFromDatabase();
		}
	
		tableView.getItems().setAll(results);
		
	}

}
