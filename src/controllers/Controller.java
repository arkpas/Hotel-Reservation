package controllers;
	

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Controller implements Initializable {
	
	private Stage window = new Stage();
	private Stage searchWindow = new Stage();
	
	@FXML private TextArea display;
	
	@Override
	public void initialize (URL url, ResourceBundle resource) {

	}
	
	@FXML
	private void openClientWindow () {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/guiAddingClient.fxml"));
			Scene scene = new Scene(root, 270, 250);
			openWindow(scene, "Client");
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void openRoomWindow () {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/guiAddingRoom.fxml"));
			Scene scene = new Scene(root, 150, 120);
			openWindow(scene, "Room");
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void openRoomTypeWindow () {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/guiAddingRoomType.fxml"));
			Scene scene = new Scene(root, 200, 250);
			openWindow(scene, "Room type");
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void openReservationWindow () {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/guiAddingReservation.fxml"));
			Scene scene = new Scene(root, 250, 200);
			openWindow(scene, "Reservation");
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void openClientSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingClient.fxml"));
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 270);
		openSearchWindow(scene, "Search client");
	}
	
	@FXML
	private void openRoomTypeSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingRoomType.fxml"));
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 600, 270);
		openSearchWindow(scene, "Search room type");
	}
	
	@FXML
	private void openRoomSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingRoom.fxml"));
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 270);
		openSearchWindow(scene, "Search room");
	}
	
	@FXML
	private void openReservationSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingReservation.fxml"));
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 270);
		openSearchWindow(scene, "Search reservation");
	}
	
	@FXML
	private void openFreeRoomSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingFreeRoom.fxml"));
		}
		catch (IOException e) {
			displayMessage("ERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 500, 270);
		openSearchWindow(scene, "Search free room");
		
	}
	
	private void openWindow (Scene scene, String title) {
		
		scene.getStylesheets().add("/view/stylesheet.css");
		window.setScene(scene);
		window.setTitle(title);
		window.setOnCloseRequest(event -> window.close());
		window.show();
	}
	
	private void openSearchWindow (Scene scene, String title) {
		
		scene.getStylesheets().add("/view/stylesheet.css");
		searchWindow.setScene(scene);
		searchWindow.setTitle(title);
		searchWindow.setOnCloseRequest(event -> searchWindow.close());
		searchWindow.show();
	}
	
	private void displayMessage (String message) {
		display.setText(message);
	}
	

}
