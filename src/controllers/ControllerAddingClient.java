package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private TextArea text;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {
		
		hotel = new Hotel();
		popupWindow = new Stage();
		
		root = new VBox();
		root.setOnKeyPressed(event -> {
			if(event.getCode().equals(KeyCode.ESCAPE))
				popupWindow.close();
				});
		root.setAlignment(Pos.CENTER);
		
		text = new TextArea();
		text.setEditable(false);
		root.getChildren().add(text);
		
		Scene scene = new Scene(root, 250, 50);
		scene.getStylesheets().add("/view/stylesheet.css");
		popupWindow.setScene(scene);
	}
	
	@FXML
	private void addClient () {
		
	
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
				List<Client> clients = hotel.searchClient(client);
				if (!clients.isEmpty()) {
					text.setText("Client has been added to database \nClientID = " + clients.get(0).getClientID());
					text.setId("green-message");
					name.clear();
					surname.clear();
					phone.clear();
					address.clear();
					city.clear();
					postalCode.clear();
				}
				else {
					text.setText("Client can not be added to database");
				}
			}
			else
				text.setText(message);
			
			hotel.disconnectFromDatabase();
		}
		else
			text.setText("Error connecting to database");
		

		
		popupWindow.show();
		popupWindow.setOnCloseRequest(action -> popupWindow.close());
	
	}
	
	@FXML
	private void enterPressedAction (KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER))
			addClient();
	}
	
}
