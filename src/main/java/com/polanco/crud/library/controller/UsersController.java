package com.polanco.crud.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.polanco.crud.library.model.TypeUser;
import com.polanco.crud.library.model.Users;

public class UsersController {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	private static EntityManager em;
	
	public static boolean loginUser(Integer docUser, String passUser) {
		boolean rsLogin = false; 
		Users aux = new Users();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = (Query) ((Object) em.createQuery("FROM Users WHERE docUsers = :doc AND passwordUsers = :pass "));
		query.setParameter("doc", docUser);
		query.setParameter("pass",passUser);
		try {
			aux = (Users) query.getSingleResult();
		}catch(NoResultException e) {
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
		try {
			if(aux.getDocUsers().equals(docUser) && aux.getPasswordUsers().equals(passUser)) {
				rsLogin = true; 
			}
		}catch(NullPointerException e) {
			System.out.println("Nulo");
		}
		em.close();		
		return rsLogin;
	}
	
	public static boolean createUser(Integer doc, String name, String lastName, String pass, Integer typeUser) {
		
		boolean rsCreateUser = false;
		EntityManager em = emf.createEntityManager();
		Users users = new Users();
		users.setDocUsers(doc);
		users.setNameUsers(name);
		users.setLastNameUsers(lastName);
		users.setPasswordUsers(pass);
		
		em.getTransaction().begin();
		TypeUser tyUser = em.find(TypeUser.class, typeUser);
		em.getTransaction().commit();
		em.close();
		
		users.setTypeUser(tyUser);
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(users);
		rsCreateUser = true;
		em.getTransaction().commit();
		em.close();
		
		return rsCreateUser;
	}
	
	public static Users searchUsersForDoc(Integer doc){
		EntityManager em = emf.createEntityManager();
		Users user = new Users();
		try {
			em.getTransaction().begin();
			Query query = (Query)(Object)em.createQuery("FROM Users WHERE docUsers = :doc");
			query.setParameter("doc", doc);
			user = (Users)query.getSingleResult();
			em.getTransaction().commit();
			em.close();				

		} catch (NoResultException ex) {
			System.out.println("No hay resultado");

		}catch(NullPointerException ex1) {
			System.out.println("Nulo");
		}
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Users> searchUsersForNameLastName(String nameLastName){
		EntityManager em = emf.createEntityManager();
		List<Users> listUsers = null;
		try {
			em.getTransaction().begin();
			Query query = (Query)(Object)em.createQuery("FROM Users WHERE nameUsers LIKE :name OR lastNameUsers LIKE :name");
			query.setParameter("name", nameLastName+"%");
			listUsers = (List<Users>)query.getResultList(); 
			em.getTransaction().commit();
			em.close();
		}catch (NoResultException ex) {
			System.out.println("No hay resultado");

		}catch(NullPointerException ex1) {
			System.out.println("Nulo");
		}
		
		return listUsers;
	}
	
	public static boolean deleteUser(int idUser) {
		em = emf.createEntityManager();
		boolean userDeleted = false;
		em.getTransaction().begin();
		Users user = em.find(Users.class, idUser);
		em.remove(user);
		userDeleted = true;
		em.getTransaction().commit();
		return userDeleted;
	}
}
