package com.polanco.crud.library.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.polanco.crud.library.model.Editorial;

public class EditorialController {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	private static EntityManager em;

	public static boolean createEditorial(String name, String address, String phone) {
		boolean editorialCreated = false;
		em = emf.createEntityManager();
		Editorial editorial = new Editorial(name, address, phone);

		em.getTransaction().begin();
		em.persist(editorial);
		editorialCreated = true;
		em.getTransaction().commit();
		em.close();
		return editorialCreated;
	}

	public static Editorial searchEditorialForCod(Integer cod) {

		Editorial editorial = new Editorial();
		em = emf.createEntityManager();

		em.getTransaction().begin();
		editorial = em.find(Editorial.class, cod);
		em.getTransaction().commit();

		return editorial;
	}

	@SuppressWarnings("unchecked")
	public static List<Editorial> searchEditorialForName(String name) {

		List<Editorial> listEditorials = null;
		em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Query query = (Query) (Object) em.createQuery("FROM Editorial WHERE nameEditorial LIKE :name");
			query.setParameter("name", "%" + name + "%");
			listEditorials = (List<Editorial>) query.getResultList();
			em.getTransaction().commit();
			em.close();
		} catch (NoResultException ex) {
			System.out.println("No hay resultado");

		} catch (NullPointerException ex1) {
			System.out.println("Nulo");
		}

		return listEditorials;
	}
	
	public static boolean deleteEditorial(Integer idEditorial) {
		em = emf.createEntityManager();
		boolean deletedEditorial = false;
		em.getTransaction().begin();
		Editorial editorial = em.find(Editorial.class, idEditorial);
		em.remove(editorial);
		deletedEditorial = true;
		em.getTransaction().commit();
		em.close();
		
		return deletedEditorial;
	} 
}
