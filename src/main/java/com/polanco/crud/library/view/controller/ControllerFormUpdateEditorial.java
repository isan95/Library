package com.polanco.crud.library.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javafx.stage.Stage;
import javafx.scene.Node;

import com.polanco.crud.library.model.Editorial;

import javafx.event.ActionEvent;

@SuppressWarnings("restriction")
public class ControllerFormUpdateEditorial {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("library"); 
	
	private EntityManager em;
	
	private Editorial editorial = new Editorial();
	
	@FXML
	private TextField txtNameEditorial;

	@FXML
	private TextField txtAddressEditorial;

	@FXML
	private TextField txtPhoneEditorial;

	@FXML
	private Button btnSaveChangeEditorial;
	
	

	public TextField getTxtNameEditorial() {
		return txtNameEditorial;
	}



	public void setTxtNameEditorial(TextField txtNameEditorial) {
		this.txtNameEditorial = txtNameEditorial;
	}



	public TextField getTxtAddressEditorial() {
		return txtAddressEditorial;
	}



	public void setTxtAddressEditorial(TextField txtAddressEditorial) {
		this.txtAddressEditorial = txtAddressEditorial;
	}



	public TextField getTxtPhoneEditorial() {
		return txtPhoneEditorial;
	}



	public void setTxtPhoneEditorial(TextField txtPhoneEditorial) {
		this.txtPhoneEditorial = txtPhoneEditorial;
	}



	public Button getSaveChangeEditorial() {
		return btnSaveChangeEditorial;
	}



	public void setSaveChangeEditorial(Button saveChangeEditorial) {
		this.btnSaveChangeEditorial = saveChangeEditorial;
	}



	@FXML
	void saveChangeEditorial(ActionEvent event) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		editorial = em.merge(editorial);
		editorial.setAddressEditorial(txtAddressEditorial.getText());
		editorial.setNameEditorial(txtNameEditorial.getText());
		editorial.setPhoneEditorial(txtPhoneEditorial.getText());
		em.getTransaction().commit();
		em.close();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	void addInfoFormUpdateEditorial(int codEditorial) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		editorial = (Editorial) em.find(Editorial.class, codEditorial);
		txtNameEditorial.setText(editorial.getNameEditorial());
		txtAddressEditorial.setText(editorial.getAddressEditorial());
		txtPhoneEditorial.setText(editorial.getPhoneEditorial());
		em.getTransaction().commit();
		em.close();
	} 
}
