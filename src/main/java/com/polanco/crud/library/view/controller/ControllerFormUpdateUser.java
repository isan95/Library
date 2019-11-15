package com.polanco.crud.library.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sound.sampled.AudioFileFormat.Type;

import com.polanco.crud.library.model.TypeUser;
import com.polanco.crud.library.model.Users;
import com.polanco.crud.library.view.UpdateUser;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class ControllerFormUpdateUser implements Initializable{
	 @FXML
	    private TextField txtDocUpdate;

	    @FXML
	    private TextField txtNameUpdate;

	    @FXML
	    private TextField txtLastNameUpdate;

	    @FXML
	    private PasswordField txtPassUpdate;

	    @FXML
	    private ComboBox<String> cbbTypeUser;

	    @FXML
	    private Button btnUpdateUser;
	    
	    private Users user = new Users();
	    
	    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	    @FXML
	    void updateUser(ActionEvent event) {
	    	
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			user = em.merge(user);
			user.setDocUsers(Integer.parseInt(txtDocUpdate.getText()));
			user.setLastNameUsers(txtLastNameUpdate.getText());
			user.setNameUsers(txtNameUpdate.getText());
			user.setPasswordUsers(txtPassUpdate.getText());
			
			TypeUser typeUserAdmin = em.find(TypeUser.class, 1);
			TypeUser typeUserUser = em.find(TypeUser.class, 2);
	
			if(cbbTypeUser.getSelectionModel().getSelectedIndex() == 0) {
				user.setTypeUser(typeUserAdmin);
			}
			else if(cbbTypeUser.getSelectionModel().getSelectedIndex() == 1) {
				user.setTypeUser(typeUserUser);
			}
			
			em.getTransaction().commit();
			em.close();
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
	    }
	    
	    
		
		@SuppressWarnings("restriction")
		public  void addInfoFormUpdateUser(int idUser) {
			
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			user = (Users)em.find(Users.class, idUser);
			
			txtPassUpdate.setText(user.getPasswordUsers());
			txtNameUpdate.setText(user.getNameUsers());
			txtLastNameUpdate.setText(user.getLastNameUsers());
			txtNameUpdate.setText(user.getNameUsers());
			cbbTypeUser.getSelectionModel().select(user.getTypeUser().getIdTypeUser()-1);
			
			em.getTransaction().commit();
			em.close();
		}
		
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			cbbTypeUser.getItems().addAll("Administrador", "Usuario");
			
		}
}
