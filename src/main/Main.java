package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {launch(args);}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("/view/gui.fxml"));

			Scene scene = new Scene(root, 700, 500);
			scene.getStylesheets().add("/view/stylesheet.css");
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hotel Reservation System");
			primaryStage.setOnCloseRequest(event -> Platform.exit());
			primaryStage.show();
	}
}


