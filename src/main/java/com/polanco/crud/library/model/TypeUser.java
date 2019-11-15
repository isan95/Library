package com.polanco.crud.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="type_user")
public class TypeUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_type_user")
	private Integer idTypeUser;
	
	@Column(name="name_type_user")
	private String nameTypeUser;

	public TypeUser() {
		
	}
	
	public TypeUser(Integer idTypeUser, String nameTypeUser) {
		this.idTypeUser = idTypeUser;
		this.nameTypeUser = nameTypeUser;
	}
	
	public Integer getIdTypeUser() {
		return idTypeUser;
	}

	public void setIdTypeUser(Integer idTypeUser) {
		this.idTypeUser = idTypeUser;
	}
	

	public String getNameTypeUser() {
		return nameTypeUser;
	}

	public void setNameTypeUser(String nameTypeUser) {
		this.nameTypeUser = nameTypeUser;
	}
	
	
	
}
