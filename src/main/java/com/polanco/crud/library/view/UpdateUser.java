package com.polanco.crud.library.view;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.stage.Modality;

public class UpdateUser{
	
	public static void main(String[] args) {
		
	}
	public  void startFormUpdateUser() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		Parent root = null;
		try {
			root = FXMLLoader.load(UpdateUser.class.getResource("/fxml/updateUser.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No se encontro el archivo updateUser.fxml");
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Actualizar usuario");
		stage.showAndWait();
	}
	
	
	

}
