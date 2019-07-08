package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;
import model.Hotel;

public class ControllerAddingClient {
	
	@FXML private TextField name = new TextField();
	@FXML private TextField surname = new TextField();
	@FXML private TextField phone = new TextField();
	@FXML private TextField address = new TextField();
	@FXML private TextField city = new TextField();
	@FXML private TextField postalCode = new TextField();
	@FXML private Button addButton = new Button();
	
	private Hotel hotel = new Hotel();
	private Stage popupWindow;
	
	
	@FXML
	private void addClient () {
		
		Label text = new Label();
		text.setId("red-message");
		
		if (hotel.connectToDatabase()) {
			String message = "";
			Client client = Client.createClient(
					name.getText(),
					surname.getText(),
					phone.getText(),
					address.getText(),
					city.getText(),
					postalCode.getText()
					);
			
			if ((message=hotel.addClient(client))==null) { //this method returns String with error message or null when there is no errors
				text.setText("Client has been added to database");
				text.setId("green-message");
			}
			else
				text.setText(message);
			
			hotel.disconnectFromDatabase();
		}
		else
			text.setText("Error connecting to database");
		
		popupWindow = new Stage();
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
