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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;
import model.Hotel;

public class ControllerAddingClient implements Initializable {
	
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private TextField phone;
	@FXML private TextField address;
	@FXML private TextField city;
	@FXML private TextField postalCode;
	@FXML private Button addButton;
	
	private Hotel hotel;
	private Stage popupWindow;
	private VBox root;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		
		hotel = new Hotel();
		popupWindow = new Stage();
		root = new VBox();
		
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 250, 50);
		scene.getStylesheets().add("/view/stylesheet.css");
		popupWindow.setScene(scene);
	}
	
	@FXML
	private void addClient () {
		
		TextArea text = new TextArea();
		text.setEditable(false);
		text.setId("red-message");
		
		if (hotel.connectToDatabase()) {
			String message = "";
			Client client = Client.parseData(
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
				name.clear();
				surname.clear();
				phone.clear();
				address.clear();
				city.clear();
				postalCode.clear();
			}
			else
				text.setText(message);
			
			hotel.disconnectFromDatabase();
		}
		else
			text.setText("Error connecting to database");
		

		root.getChildren().add(text);
		popupWindow.show();
		popupWindow.setOnCloseRequest(action -> popupWindow.close());
	
	}
	
}
