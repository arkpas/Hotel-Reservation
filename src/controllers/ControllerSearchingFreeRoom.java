package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Hotel;
import model.ReservationJoin;

public class ControllerSearchingFreeRoom implements Initializable {
	
	@FXML private DatePicker dateFromPicker = new DatePicker();
	@FXML private DatePicker dateToPicker = new DatePicker();
	
	private Hotel hotel;
	
	@FXML private TableView<ReservationJoin> tableView;
	@FXML private TableColumn<ReservationJoin, Integer> roomIDCol;
	@FXML private TableColumn<ReservationJoin, String> roomTypeCol;
	@FXML private TableColumn<ReservationJoin, Double> priceCol;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		
		hotel = new Hotel();
		
		roomIDCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, Integer>("roomID"));
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, String>("roomType"));
		priceCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, Double>("price"));

	}
	
	@FXML
	private void searchFreeRoom () {
		
		ReservationJoin rj = ReservationJoin.parseFreeRoomSearch(dateFromPicker.getValue(), dateToPicker.getValue());
		
		List<ReservationJoin> results = new ArrayList<>();
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.searchFreeRoom(rj));
			hotel.disconnectFromDatabase();
		}
		
		tableView.getItems().setAll(results);
	}
	
	@FXML
	private void enterPressedAction (KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER))
			searchFreeRoom();
	}

}
