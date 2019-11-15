package com.polanco.crud.library.view.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.polanco.crud.library.controller.UsersController;
import com.polanco.crud.library.model.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class ControllerLogin implements Initializable {
	private EntityManagerFactory emf;
	private EntityManager em;

	@FXML
	private TextField txtDoc;

	@FXML
	private PasswordField txtpass;

	@FXML
	private Button btnIntro;

	@FXML
	void signIn(ActionEvent e) {

		if (UsersController.loginUser(Integer.parseInt(txtDoc.getText()), txtpass.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacion");
			alert.setHeaderText(null);
			alert.setContentText("Iniciaste sesion correctamente");
			alert.showAndWait();
			changeScene(e);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacion");
			alert.setHeaderText(null);
			alert.setContentText("Usuario o contrase�a incorrectos");
			alert.showAndWait();
		}

	}

	public void changeScene(ActionEvent e) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
			Scene newScene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(newScene);
			window.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		emf = Persistence.createEntityManagerFactory("library");
	}

}
