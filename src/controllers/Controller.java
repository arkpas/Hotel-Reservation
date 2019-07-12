package controllers;
	

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Controller {
	
	private Stage window = new Stage();
	private Stage searchWindow = new Stage();
	
	@FXML private TextArea display = new TextArea();
	
	@FXML
	private void openClientWindow () {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/guiAddingClient.fxml"));
			Scene scene = new Scene(root, 270, 250);
			openWindow(scene, "Client");
		}
		catch (IOException e) {
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
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
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
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
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
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
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
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
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 270);
		scene.getStylesheets().add("/view/stylesheet.css");
		searchWindow.setScene(scene);
		searchWindow.setTitle("Search client");
		searchWindow.setOnCloseRequest(event -> searchWindow.close());
		searchWindow.show();
	}
	
	@FXML
	private void openRoomTypeSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingRoomType.fxml"));
		}
		catch (IOException e) {
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 270);
		scene.getStylesheets().add("/view/stylesheet.css");
		searchWindow.setScene(scene);
		searchWindow.setTitle("Search room type");
		searchWindow.setOnCloseRequest(event -> searchWindow.close());
		searchWindow.show();
	}
	
	@FXML
	private void openRoomSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingRoom.fxml"));
		}
		catch (IOException e) {
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 270);
		scene.getStylesheets().add("/view/stylesheet.css");
		searchWindow.setScene(scene);
		searchWindow.setTitle("Search room");
		searchWindow.setOnCloseRequest(event -> searchWindow.close());
		searchWindow.show();
	}
	
	@FXML
	private void openReservationSearchWindow () {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/guiSearchingReservation.fxml"));
		}
		catch (IOException e) {
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 270);
		scene.getStylesheets().add("/view/stylesheet.css");
		searchWindow.setScene(scene);
		searchWindow.setTitle("Search reservation");
		searchWindow.setOnCloseRequest(event -> searchWindow.close());
		searchWindow.show();
	}
	
	private void openWindow (Scene scene, String title) {
		
		scene.getStylesheets().add("/view/stylesheet.css");
		window.setScene(scene);
		window.setTitle(title);
		window.setOnCloseRequest(event -> window.close());
		window.show();
	}
	

}
