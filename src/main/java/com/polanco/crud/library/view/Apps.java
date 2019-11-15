package com.polanco.crud.library.view;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Apps extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
