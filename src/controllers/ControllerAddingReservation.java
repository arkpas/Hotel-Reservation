package controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.Reservation;

public class ControllerAddingReservation {
	
	@FXML private TextField clientIDField = new TextField();
	@FXML private TextField roomIDField = new TextField();
	@FXML private DatePicker dateFromPicker = new DatePicker();
	@FXML private DatePicker dateToPicker = new DatePicker();
	
	private Stage popupWindow = new Stage();
	private Hotel hotel = new Hotel();
	
	@FXML
	private void addReservation () {
		Label text = new Label();
		text.setId("red-message");
		boolean isDataValid = false;
		
		int clientID = 0;
		int roomID = 0;
		LocalDate dateFrom = null;
		LocalDate dateTo = null;
		
		try {
			clientID = Integer.parseInt(clientIDField.getText());
			roomID = Integer.parseInt(roomIDField.getText());
			dateFrom = dateFromPicker.getValue();
			dateTo = dateToPicker.getValue();
			if (!dateFrom.isBefore(LocalDate.now()) && !dateTo.isBefore(dateFrom))
				isDataValid = true;
			else
				text.setText("Date is not valid - reserving for past dates is forbidden" );
		}
		catch (NumberFormatException e) {
			text.setText("Incorrect data - Client ID and Room Number should be numbers");
		}
		
		if (isDataValid) {
			
			
			if (hotel.connectToDatabase()) {
				Reservation reservation = Reservation.createReservation(clientID, roomID, dateFrom, dateTo);
				String message = "";
				if ((message = hotel.addReservation(reservation)) == null) {
					text.setId("green-message");
					text.setText("Reservation has been made");
				}
				else
					text.setText(message);
				hotel.disconnectFromDatabase();
			}
			else
				text.setText("Error connecting to database");
		}
		
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(text);
		Scene scene = new Scene(root, 400, 30);
		scene.getStylesheets().add("/view/stylesheet.css");
		popupWindow.setScene(scene);
		popupWindow.show();
		popupWindow.setOnCloseRequest(action -> popupWindow.close());
		
		
	}
	
}
