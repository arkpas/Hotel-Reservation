package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.Room;

public class ControllerAddingRoom implements Initializable {
	
	@FXML private TextField roomIDfield;
	@FXML private TextField roomTypeIDfield;
	@FXML private Button addButton = new Button();
	
	private Hotel hotel;
	private Stage popupWindow;
	private VBox root;
	private TextArea text;
	
	
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		hotel = new Hotel();
		popupWindow = new Stage();
		
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setOnKeyPressed(event -> {
			if(event.getCode().equals(KeyCode.ESCAPE))
				popupWindow.close();
				});
		
		text = new TextArea();
		text.setEditable(false);
		text.setWrapText(true);
		root.getChildren().add(text);
		
		Scene scene = new Scene(root, 400, 50);
		scene.getStylesheets().add("/view/stylesheet.css");
		popupWindow.setScene(scene);
	}
	
	@FXML
	private void addRoom () {
		
		boolean isDataValid = false;
		int roomID = 0;
		int roomTypeID = 0;
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
		


		popupWindow.show();
		popupWindow.setOnCloseRequest(action -> popupWindow.close());
		
	}
	

}
