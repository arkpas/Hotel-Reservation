package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.Room;

public class ControllerAddingRoom {
	
	@FXML private TextField roomIDfield;
	@FXML private TextField roomTypeIDfield;
	@FXML private Button addButton = new Button();
	
	private Hotel hotel = new Hotel();
	private Stage popupWindow = new Stage();
	
	@FXML
	private void addRoom () {
		
		boolean isDataValid = false;
		int roomID = 0;
		int roomTypeID = 0;
		Label text = new Label();
		text.setId("red-message");
		
		try {
			roomID = Integer.parseInt(roomIDfield.getText());
			roomTypeID = Integer.parseInt(roomTypeIDfield.getText());
			if (roomID < 0) {
				text.setText("Room number can not be less than zero");
			}
			else 
				isDataValid = true;
			}
		catch (NumberFormatException e) {
			text.setText("Please enter only numbers.");
		}
		
		if (isDataValid) {
			if (hotel.connectToDatabase()) {
				String message = "";
				if ((message=hotel.addRoom(new Room(roomID, roomTypeID))) == null) {	//this method returns String with error message or null when there is no errors
					text.setText("Room has been added to database.");
					text.setId("green-message");
				}
				else
					text.setText(message);
				
				hotel.disconnectFromDatabase();
			}
			else
				text.setText("Error connecting to database.");
		}
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(text);
		Scene scene = new Scene(root, 250, 50);
		scene.getStylesheets().add("/view/stylesheet.css");
		popupWindow.setScene(scene);
		popupWindow.show();
		popupWindow.setOnCloseRequest(action -> popupWindow.close());
		
	}
	

}
