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
import model.RoomJoinRoomType;

public class ControllerSearchingRoom implements Initializable {
	
	@FXML private TextField roomIDField;
	@FXML private TextField roomTypeIDField;
	@FXML private TextField roomTypeField;
	@FXML private TextField priceField;
	
	private Hotel hotel = new Hotel();
	
	@FXML private TableView<RoomJoinRoomType> tableView;
	@FXML private TableColumn<RoomJoinRoomType, Integer> roomIDCol;
	@FXML private TableColumn<RoomJoinRoomType, Integer> roomTypeIDCol;
	@FXML private TableColumn<RoomJoinRoomType, String> nameCol;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		roomIDCol.setCellValueFactory(new PropertyValueFactory<RoomJoinRoomType, Integer>("roomID"));
		roomTypeIDCol.setCellValueFactory(new PropertyValueFactory<RoomJoinRoomType, Integer>("roomTypeID"));
		nameCol.setCellValueFactory(new PropertyValueFactory<RoomJoinRoomType, String>("name"));
	}
	
	@FXML
	private void searchRoom () {
		
		List<RoomJoinRoomType> results = new ArrayList<>();
		
		RoomJoinRoomType rjrt = RoomJoinRoomType.parseDataForSearch(roomIDField.getText(), roomTypeIDField.getText(), roomTypeField.getText(), priceField.getText());
		
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchRoom(rjrt));
			hotel.disconnectFromDatabase();
		}
	
		tableView.getItems().setAll(results);
		
	}

}
