package com.polanco.crud.library.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="editorial")
public class Editorial implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue()
	@Column(name = "id_editorial")
	private Integer idEditorial;
	
	@Column(name = "name_editorial")
	private String nameEditorial;
	
	@Column(name = "address_editorial")
	private String addressEditorial;
	
	@Column(name = "phone_editorial")
	private String phoneEditorial;
	
	

	public Editorial() {
		
	}

	public Editorial(Integer idEditorial, String nameEditorial, String addressEditorial, String phoneEditorial) {
	
		this.idEditorial = idEditorial;
		this.nameEditorial = nameEditorial;
		this.addressEditorial = addressEditorial;
		this.phoneEditorial = phoneEditorial;
	}
	
	

	public Editorial(String nameEditorial, String addressEditorial, String phoneEditorial) {
		
		this.nameEditorial = nameEditorial;
		this.addressEditorial = addressEditorial;
		this.phoneEditorial = phoneEditorial;
	}

	public Integer getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(Integer idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNameEditorial() {
		return nameEditorial;
	}

	public void setNameEditorial(String nameEditorial) {
		this.nameEditorial = nameEditorial;
	}

	public String getAddressEditorial() {
		return addressEditorial;
	}

	public void setAddressEditorial(String addressEditorial) {
		this.addressEditorial = addressEditorial;
	}

	public String getPhoneEditorial() {
		return phoneEditorial;
	}

	public void setPhoneEditorial(String phoneEditorial) {
		this.phoneEditorial = phoneEditorial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
