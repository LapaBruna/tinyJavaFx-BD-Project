package com.eli.projeto.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Index.fxml"));
			Scene cena = new Scene(p);

			primaryStage.setScene(cena);
			primaryStage.show();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
