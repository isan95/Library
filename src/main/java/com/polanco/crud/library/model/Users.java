package com.polanco.crud.library.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class Users implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
    @GeneratedValue
    @Column(name="id_Users")
    private int idUsers;

    @Column(name="doc_users")
    private Integer docUsers;

    @Column(name="name_users")
    private String nameUsers;

    @Column(name="last_name_usres")
    private String lastNameUsers;

    @Column(name="password_users")
    private String passwordUsers;
    
    @ManyToOne()
    @JoinColumn(name="typeUser_id_type_user")
    private TypeUser typeUser;
    
    public Users() {
		
	}

	public Users(int idUsers, Integer docUsers, String nameUsers, String lastNameUsers, String passwordUsers, TypeUser typeUser) {
		this.idUsers = idUsers;
		this.docUsers = docUsers;
		this.nameUsers = nameUsers;
		this.lastNameUsers = lastNameUsers;
		this.passwordUsers = passwordUsers;
		this.typeUser = typeUser;
	}
	
	
	public Users(Integer docUsers, String nameUsers, String lastNameUsers, String passwordUsers) {

		this.docUsers = docUsers;
		this.nameUsers = nameUsers;
		this.lastNameUsers = lastNameUsers;
		this.passwordUsers = passwordUsers;
	}

	
	
	public Users(int idUsers, Integer docUsers, String nameUsers, String lastNameUsers, TypeUser typeUser) {
		super();
		this.idUsers = idUsers;
		this.docUsers = docUsers;
		this.nameUsers = nameUsers;
		this.lastNameUsers = lastNameUsers;
		this.typeUser = typeUser;
	}

	public int getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public Integer getDocUsers() {
		return docUsers;
	}

	public void setDocUsers(Integer docUsers) {
		this.docUsers = docUsers;
	}

	public String getNameUsers() {
		return nameUsers;
	}

	public void setNameUsers(String nameUsers) {
		this.nameUsers = nameUsers;
	}

	public String getLastNameUsers() {
		return lastNameUsers;
	}

	public void setLastNameUsers(String lastNameUsers) {
		this.lastNameUsers = lastNameUsers;
	}

	public String getPasswordUsers() {
		return passwordUsers;
	}

	public void setPasswordUsers(String passwordUsers) {
		this.passwordUsers = passwordUsers;
	}

	public TypeUser getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(TypeUser typeUser) {
		this.typeUser = typeUser;
	}
	
	

    

}