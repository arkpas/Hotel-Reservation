package controllers;
	

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.*;


public class Controller {
	
	private Hotel hotel = new Hotel();
	private Stage window = new Stage();
	
	@FXML private TextArea display = new TextArea();
	
	@FXML
	private void getClients() {
		if (hotel.connectToDatabase()) {
			display.setText(display.getText() + "\n\nCLIENTS");
			for (Client c : hotel.getClients()) {
				display.setText(display.getText() + "\n" + c);
			}
			hotel.disconnectFromDatabase();
		}
		else {
			display.setText(display.getText() + "\n" + "Error connecting to database");
		}
		display.end();
	}
	
	@FXML
	private void getRooms() {
		
		if (hotel.connectToDatabase()) {
			display.setText(display.getText() + "\n\nROOMS");
			for (Room r : hotel.getRooms()) {
				display.setText(display.getText() + "\n" + r);
			}
			hotel.disconnectFromDatabase();
		}
		else {
			display.setText(display.getText() + "\n" + "Error connecting to database");
		}
		display.end();
	}
	
	@FXML
	private void getRoomTypes() {
		
		if (hotel.connectToDatabase()) {
			display.setText(display.getText() + "\n\nROOM TYPES");
			for (RoomType rt : hotel.getRoomTypes()) {
				display.setText(display.getText() + "\n" + rt);
			}
			hotel.disconnectFromDatabase();
		}
		else {
			display.setText(display.getText() + "\n" + "Error connecting to database");
		}
		display.end();
	}
	
	@FXML
	private void getReservations() {
		
		if (hotel.connectToDatabase()) {
			display.setText(display.getText() + "\n\nRESERVATIONS");
			for (Reservation r : hotel.getReservations()) {
				display.setText(display.getText() + "\n" + r);
			}
			hotel.disconnectFromDatabase();
		}
		else {
			display.setText(display.getText() + "\n" + "Error connecting to database");
		}
		display.end();
	}
	
	@FXML
	private void openClientWindow () {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/guiAddingClient.fxml"));
			Scene scene = new Scene(root, 400, 250);
			openWindow(scene);
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
			Scene scene = new Scene(root, 200, 100);
			openWindow(scene);
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
			openWindow(scene);
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
			Scene scene = new Scene(root, 200, 200);
			openWindow(scene);
		}
		catch (IOException e) {
			display.setText(display.getText() + "\n\nERROR OPENING NEW WINDOW!");
			e.printStackTrace();
		}
		
	}
	
	private void openWindow (Scene scene) {
		
		scene.getStylesheets().add("/view/stylesheet.css");
		window.setScene(scene);
		window.setTitle("Add Room Type");
		window.setOnCloseRequest(event -> window.close());
		window.show();
	}
	

}
