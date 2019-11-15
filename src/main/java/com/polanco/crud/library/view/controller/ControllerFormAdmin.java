package com.polanco.crud.library.view.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.polanco.crud.library.controller.EditorialController;
import com.polanco.crud.library.controller.UsersController;
import com.polanco.crud.library.model.Editorial;
import com.polanco.crud.library.model.TypeUser;
import com.polanco.crud.library.model.Users;
import com.polanco.crud.library.view.UpdateUser;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

@SuppressWarnings("restriction")
public class ControllerFormAdmin implements Initializable {
	private EntityManagerFactory emf;
	private EntityManager em;
	@FXML
	private Button btnCreateUsers;

	@FXML
	private Button btnSearchUser;

	@FXML
	private AnchorPane center;

	@FXML
	private AnchorPane paneSearchUser;

	@FXML
	private ComboBox<String> cbSearchParameter;

	@FXML
	private Button btnDeleteUser;

	@FXML
	private Button btnEditUser;

	@FXML
	private AnchorPane paneCreateUser;

	@FXML
	private TextField txtDoc;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtLastName;

	@FXML
	private PasswordField txtPass;

	@FXML
	private TextField txtSearch;

	@FXML
	private TableView<Users> tblUsers;

	@FXML
	private TableColumn<Users, Integer> colId;

	@FXML
	private TableColumn<Users, Long> colNoDoc;

	@FXML
	private TableColumn<Users, String> colName;

	@FXML
	private TableColumn<Users, String> colLastName;

	@FXML
	private TableColumn<Users, String> colType;

	@FXML
	private Button pruebaAgregar;

	@FXML
	private Button btnSearch;

	@FXML
	private ComboBox<String> cbbTypeUser;

	@FXML
	private Button btnActiveFormCreateEditorial;

	@FXML
	private Button btnActiveFormSearchEditorial;

	@FXML
	private AnchorPane frmCreateEditorial;

	@FXML
	private TableView<Editorial> tblEditorial;

	@FXML
	private TableColumn<Editorial, Integer> colCod;

	@FXML
	private TableColumn<Editorial, String> colNameEditorial;

	@FXML
	private TableColumn<Editorial, String> colAdress;

	@FXML
	private TableColumn<Editorial, String> colPhone;

	@FXML
	private Button btnDeleteEditorial;

	@FXML
	private Button btnUpdateEditorial;

	@FXML
	private TextField txtSearchParameter;

	@FXML
	private ComboBox<String> cbbCodOrNameEditorial;

	@FXML
	private Button btnSearchEditorial;

	@FXML
	private AnchorPane frmSearchEditorial;

	@FXML
	private TextField txtNameEditorial;

	@FXML
	private TextField txtAdressEditorial;

	@FXML
	private TextField txtPhoneEditorial;

	@FXML
	private Button btnSaveEditorial;

	@FXML
	void activeFormEditorial(ActionEvent event) {
		frmCreateEditorial.toFront();
	}
	@FXML
	void activeFormSearchEditorial(ActionEvent event) {
		frmSearchEditorial.toFront();
	}
	
	@FXML
	void deleteEditorial(ActionEvent event) {
		if(tblEditorial.getSelectionModel().getSelectedItem() != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Eliminar usuario");
			alert.setHeaderText(null);
			alert.setContentText("¿Seguro que desea eliminar el usuario seleccionado?");

			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.YES) {

				if (EditorialController.deleteEditorial(tblEditorial.getSelectionModel().getSelectedItem().getIdEditorial())) {
					Alert alertDeleted = new Alert(AlertType.INFORMATION);
					alertDeleted.setTitle("Mensaje");
					alertDeleted.setHeaderText(null);
					alertDeleted.setContentText("Editorial ha sido eliminada");
				} else {
					Alert alertError = new Alert(AlertType.INFORMATION);
					alertError.setTitle("Mensaje");
					alertError.setHeaderText(null);
					alertError.setContentText("Problemas al eliminar la editorial");
				}
			} else {

			}
			
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mensaje");
			alert.setHeaderText(null);
			alert.setContentText("Debes selecionar una fila");
		}
		
	}
	
	@FXML
	void cbbCodOrNameEditorialAction(ActionEvent event) {
		if(cbbCodOrNameEditorial.getSelectionModel().getSelectedItem().equals("Codigo")) {
			btnSearchEditorial.setVisible(true);
		}
		else {
			btnSearchEditorial.setVisible(false);
		}
	}
	
	@FXML
	void searchEditorialForName(KeyEvent event) {
		if(cbbCodOrNameEditorial.getSelectionModel() != null) {
			if(cbbCodOrNameEditorial.getSelectionModel().getSelectedItem().equals("Nombre")) {
				tblEditorial.getItems().clear();
				tblEditorial.getItems().addAll(EditorialController.searchEditorialForName(txtSearchParameter.getText()));
			}
		
		}
		else if(cbbCodOrNameEditorial.getSelectionModel() == null) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mensaje");
			alert.setHeaderText(null);
			alert.setContentText("Seleccione un criterio de busqueda");
			alert.showAndWait();
		}
	}
	
	@FXML
	void searchEditorial(ActionEvent e) {
		if(cbbCodOrNameEditorial != null) {
			if(cbbCodOrNameEditorial.getSelectionModel().getSelectedItem().equals("Codigo")) {
				tblEditorial.getItems().clear();
				tblEditorial.getItems().add(EditorialController.searchEditorialForCod(Integer.parseInt(txtSearchParameter.getText())));
				
			}
		}
		else if(cbbCodOrNameEditorial.getSelectionModel() == null){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mensaje");
			alert.setHeaderText(null);
			alert.setContentText("Seleccione un criterio de busqueda");
			alert.showAndWait();	
		}
	}

	@FXML
	void saveEditorial(ActionEvent event) {
		if(EditorialController.createEditorial(txtNameEditorial.getText(), txtAdressEditorial.getText(), txtPhoneEditorial.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mensaje");
			alert.setHeaderText(null);
			alert.setContentText("Editorial creada con exito");
			alert.showAndWait();			
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Error al registrar la editorial");
			alert.showAndWait();
		}
	}
	
	@FXML
	void updateEditorial(ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updateEditorial.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		}catch(IOException e) {
			System.out.println("No se encontró updateEditorial.fxml");
			e.printStackTrace();;
		}
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setScene(new Scene(root));
		stage.setTitle("Actualizar editorial");
		ControllerFormUpdateEditorial controllerFormUpdateEditorial = loader.getController();
		controllerFormUpdateEditorial.addInfoFormUpdateEditorial(tblEditorial.getSelectionModel().getSelectedItem().getIdEditorial());
		stage.showAndWait();
		
	}

	@FXML
	void activeFormCreateUsers(ActionEvent event) {
		paneCreateUser.toFront();
	}

	@FXML
	void activeFormSearchUsers(ActionEvent event) {
		paneSearchUser.toFront();
	}

	@FXML
	void searchParameter(ActionEvent event) {
		if (cbSearchParameter.getSelectionModel().getSelectedItem().equals("Documento")) {
			btnSearch.setVisible(true);
		} else {
			btnSearch.setVisible(false);
		}
	}

	@FXML
	public void createUsers() {
		Integer typeUser = 0;
		if (cbbTypeUser.getSelectionModel().getSelectedIndex() == 0) {
			typeUser = 1;
		} else if (cbbTypeUser.getSelectionModel().getSelectedIndex() == 1) {
			typeUser = 2;
		}

		if (UsersController.createUser(Integer.parseInt(txtDoc.getText()), txtName.getText(), txtLastName.getText(),
				txtPass.getText(), typeUser)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacion");
			alert.setHeaderText(null);
			alert.setContentText("Usuario creado correctamente");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacion");
			alert.setHeaderText(null);
			alert.setContentText("Problemas al registrar el uusaurio en la base de datos");
			alert.showAndWait();
		}

	}

	@FXML
	void updateUser(ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updateUser.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			System.out.println("No se encontro updateUser.fxml");
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setScene(new Scene(root));
		stage.setTitle("Actualizar usuario");
		ControllerFormUpdateUser controllerFormUpdateUser = loader.getController();
		controllerFormUpdateUser.addInfoFormUpdateUser(tblUsers.getSelectionModel().getSelectedItem().getIdUsers());
		stage.showAndWait();

	}

	@FXML
	void deleteUser(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Eliminar usuario");
		alert.setHeaderText(null);
		alert.setContentText("¿Seguro que desea eliminar el usuario seleccionado?");

		Optional<ButtonType> result = alert.showAndWait();

		if (tblUsers.getSelectionModel().getSelectedItem() != null) {
			if (result.get() == ButtonType.YES) {

				if (UsersController.deleteUser(tblUsers.getSelectionModel().getSelectedItem().getIdUsers())) {
					Alert alertDeleted = new Alert(AlertType.INFORMATION);
					alertDeleted.setTitle("Mensaje");
					alertDeleted.setHeaderText(null);
					alertDeleted.setContentText("Usuario ha sido eliminado");
				} else {
					Alert alertError = new Alert(AlertType.INFORMATION);
					alertError.setTitle("Mensaje");
					alertError.setHeaderText(null);
					alertError.setContentText("Problemas al eliminar el usuario");
				}
			} else {

			}
		} else {
			Alert alertDelUser = new Alert(AlertType.INFORMATION);
			alertDelUser.setTitle("Mensaje");
			alertDelUser.setHeaderText(null);
			alertDelUser.setContentText("Debes seeleccionar una fila");

		}

	}

	@FXML
	void searchUsers(KeyEvent event) {

		if (cbSearchParameter.getSelectionModel().getSelectedItem().equals("Nombre y apellido")
				&& cbSearchParameter.getSelectionModel().getSelectedItem() != null) {
			tblUsers.getItems().clear();
			tblUsers.getItems().addAll(UsersController.searchUsersForNameLastName(txtSearch.getText()));

		}

	}

	@FXML
	void searchForDoc(ActionEvent e) {
		if (cbSearchParameter.getSelectionModel().getSelectedItem().equals("Documento")
				&& cbSearchParameter.getSelectionModel().getSelectedItem() != null) {
			tblUsers.getItems().clear();
			tblUsers.getItems().add(UsersController.searchUsersForDoc(Integer.parseInt(txtSearch.getText())));

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbSearchParameter.getItems().addAll("Documento", "Nombre y apellido");
		cbbTypeUser.getItems().addAll("Administrador", "Usuario");
		cbbCodOrNameEditorial.getItems().addAll("Codigo","Nombre");
		emf = Persistence.createEntityManagerFactory("library");

		colId.setCellValueFactory(new PropertyValueFactory<Users, Integer>("idUsers"));
		colNoDoc.setCellValueFactory(new PropertyValueFactory<Users, Long>("docUsers"));
		colName.setCellValueFactory(new PropertyValueFactory<Users, String>("nameUsers"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Users, String>("lastNameUsers"));
		colType.setCellValueFactory(new PropertyValueFactory<Users, String>("typeUser"));
		
		colCod.setCellValueFactory(new PropertyValueFactory<Editorial, Integer>("idEditorial"));;
		colNameEditorial.setCellValueFactory(new PropertyValueFactory<Editorial, String>("nameEditorial"));
		colAdress.setCellValueFactory(new PropertyValueFactory<Editorial, String>("addressEditorial"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Editorial, String>("phoneEditorial"));
		
		btnSearchEditorial.setVisible(false);
		btnSearch.setVisible(false);

	}

}
