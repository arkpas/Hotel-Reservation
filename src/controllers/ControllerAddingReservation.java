package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.Reservation;

public class ControllerAddingReservation implements Initializable {
	
	@FXML private TextField clientIDField = new TextField();
	@FXML private TextField roomIDField = new TextField();
	@FXML private DatePicker dateFromPicker = new DatePicker();
	@FXML private DatePicker dateToPicker = new DatePicker();
	
	private Stage popupWindow;
	private Hotel hotel;
	private VBox root;
	private TextArea text;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {

		popupWindow = new Stage();
		hotel = new Hotel();
		
		root = new VBox();
		root.setOnKeyPressed(event -> {
			if(event.getCode().equals(KeyCode.ESCAPE))
				popupWindow.close();
				});
		root.setAlignment(Pos.CENTER);
		
		text = new TextArea();
		text.setEditable(false);
		text.setWrapText(true);
		root.getChildren().add(text);
		
		Scene scene = new Scene(root, 400, 50);
		scene.getStylesheets().add("/view/stylesheet.css");
		popupWindow.setScene(scene);
	}
	
	@FXML
	private void addReservation () {

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
		

		popupWindow.show();
		popupWindow.setOnCloseRequest(action -> popupWindow.close());
		
		
	}
	
}
