package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.RoomType;

public class ControllerAddingRoomType implements Initializable {

	@FXML private TextField nameField;
	@FXML private TextField sizeField;
	@FXML private TextField bedsField;
	@FXML private ComboBox<String> balconyBox;
	@FXML private ComboBox<String> bathroomBox;
	@FXML private TextField priceField;
	
	private Stage popupWindow;
	private Hotel hotel;
	private VBox root;
	private TextArea text;
	
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		balconyBox.getItems().addAll("yes", "no");
		balconyBox.setValue(balconyBox.getItems().get(0));
		bathroomBox.getItems().addAll("yes", "no");
		bathroomBox.setValue(bathroomBox.getItems().get(0));
		
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
	private void addRoomType () {
	
		text.setId("red-message");
		
		RoomType roomType = RoomType.parseData(nameField.getText(), sizeField.getText(), bedsField.getText(), balconyBox.getValue(), bathroomBox.getValue(), priceField.getText());
	
		if (roomType == null)
			text.setText("Provided data is incorrect");
		else {
			if (hotel.connectToDatabase()) {
				String message = "";
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
		
	
		popupWindow.show();
		popupWindow.setOnCloseRequest(action -> popupWindow.close());
		
	}
	
}
