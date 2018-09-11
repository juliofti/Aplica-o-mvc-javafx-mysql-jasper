package com.company.visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application{

	public static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		 Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("Sistema (JavaFX MVC)");
	        stage.setResizable(false);
	        setStage(stage);
	        stage.show();
	       
	}

	public static void main(String []args) {
		launch();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		main.stage = stage;
	}
	

}
