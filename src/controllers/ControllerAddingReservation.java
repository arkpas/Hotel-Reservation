package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.Reservation;

public class ControllerAddingReservation {
	
	@FXML private TextField clientIDField = new TextField();
	@FXML private TextField roomIDField = new TextField();
	@FXML private TextField dateFromField = new TextField();
	@FXML private TextField dateToField = new TextField();
	
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
			dateFrom = LocalDate.parse(dateFromField.getText());
			dateTo = LocalDate.parse(dateToField.getText());
			isDataValid = true;
		}
		catch (NumberFormatException e) {
			text.setText("Incorrect data - Client ID and Room Number should be numbers");
		}
		catch (DateTimeParseException e) {
			text.setText("Date should be in format YYYY-MM-DD");
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
