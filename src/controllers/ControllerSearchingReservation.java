package controllers;

import java.net.URL;
import java.time.LocalDate;
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
import model.ReservationJoin;

public class ControllerSearchingReservation implements Initializable {
	
	@FXML private TextField reservationIDField;
	@FXML private TextField clientIDField;
	@FXML private TextField roomIDField;
	
	private Hotel hotel;
	
	@FXML private TableView<ReservationJoin> tableView;
	@FXML private TableColumn<ReservationJoin, Integer> reservationIDCol;
	@FXML private TableColumn<ReservationJoin, Integer> clientIDCol;
	@FXML private TableColumn<ReservationJoin, Integer> roomIDCol;
	@FXML private TableColumn<ReservationJoin, LocalDate> dateFromCol;
	@FXML private TableColumn<ReservationJoin, LocalDate> dateToCol;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		
		hotel = new Hotel();
		
		reservationIDCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, Integer>("reservationID"));
		clientIDCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, Integer>("clientID"));
		roomIDCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, Integer>("roomID"));
		dateFromCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, LocalDate>("dateFrom"));
		dateToCol.setCellValueFactory(new PropertyValueFactory<ReservationJoin, LocalDate>("dateTo"));
	}
	
	@FXML
	private void searchReservations () {
		
		List<ReservationJoin> results = new ArrayList<>();
		if (hotel.connectToDatabase()) {
			results.addAll(hotel.getReservations());
			hotel.disconnectFromDatabase();
		}
		
		tableView.getItems().setAll(results);
	}

}
