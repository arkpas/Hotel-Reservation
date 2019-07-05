package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	private Stage errorPopup;
	
	
	@FXML
	private void addClient () {
		if (hotel.connectToDatabase()) {
			Client client = Client.createClient(
					name.getText(),
					surname.getText(),
					phone.getText(),
					address.getText(),
					city.getText(),
					postalCode.getText()
					);
			hotel.addClient(client);
			hotel.disconnectFromDatabase();
		}
		else {
			errorPopup = new Stage();
			VBox root = new VBox();
			Text text = new Text("ERROR CONNECTING TO DATABASE!");
			root.getChildren().add(text);
			
			errorPopup.setScene(new Scene(root, 50,50));
			errorPopup.setOnCloseRequest(event -> errorPopup.close());
			errorPopup.show();
		}
		
		Stage stage = (Stage) addButton.getScene().getWindow();
		stage.close();
	}
	
}
