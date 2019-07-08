package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.RoomType;

public class ControllerAddingRoomType {

	@FXML private TextField nameField = new TextField();
	@FXML private TextField sizeField = new TextField();
	@FXML private TextField bedsField = new TextField();
	@FXML private RadioButton balconyButton = new RadioButton();
	@FXML private RadioButton bathroomButton = new RadioButton();
	@FXML private TextField priceField = new TextField();
	
	private Stage popupWindow = new Stage();
	private Hotel hotel = new Hotel();
	
	
	@FXML
	private void addRoomType () {
		
		Label text = new Label();
		text.setId("red-message");
		boolean isDataValid = false;
		
		int size = 0;
		int beds = 0;
		boolean balcony = false;
		boolean bathroom = false;
		double price = 0;
		

		
		
		try {
			size = Integer.parseInt(sizeField.getText());
			beds = Integer.parseInt(bedsField.getText());
			balcony = balconyButton.isSelected();
			bathroom = bathroomButton.isSelected();
			price = Double.parseDouble(priceField.getText());
			isDataValid = true;
		}
		catch (NumberFormatException e) {
			text.setText("Incorrect data - size, beds and price should be numbers");
		}
		if (isDataValid) {
			if (hotel.connectToDatabase()) {
				String message = "";
				RoomType roomType = RoomType.createRoomType(nameField.getText(), size, beds, balcony, bathroom, price);
				if ((message = hotel.addRoomType(roomType)) == null) {
					text.setText("Room type has been added to database");
					text.setId("green-message");
				}
				else
					text.setText(message);
			}
			else
				text.setText("Error connecting to database");
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
