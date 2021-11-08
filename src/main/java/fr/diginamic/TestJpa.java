package fr.diginamic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOneToManyCollectionElementType;

public class TestJpa {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("diginamic");
		EntityManager em = emf.createEntityManager();
		
		Livre livreATrouver = em.find(Livre.class,3);
		if (livreATrouver!=null) {
			System.out.println(livreATrouver);
		}
		
		
		
		Livre livreAAjouter = new Livre();
		livreAAjouter.setAuteur("Roald Dahl");
		livreAAjouter.setTitre("Matilda");
		System.out.println(livreAAjouter);
		
		
		Livre livreAModifier = em.find(Livre.class, 5);
		livreAModifier.setTitre("Du Plaisir Dans La Cuisine");
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		//em.persist(livreAAjouter);
		em.merge(livreAModifier);
		et.commit();
		
		
		
		TypedQuery<Livre> tqLivreParTitre = em.createQuery("select l from Livre l where l.titre='Matilda'",Livre.class);
		Livre livreParTitre = tqLivreParTitre.getResultList().get(0);
		System.out.println(livreParTitre);
		
		TypedQuery<Livre> tqListeLivre = em.createQuery("select l from Livre l",Livre.class);
		List<Livre> listeLivre = new ArrayList<>();
		for (int i = 0; i < tqListeLivre.getResultList().size(); i++) {
			Livre ajoutListeLivre = tqListeLivre.getResultList().get(i);
			listeLivre.add(ajoutListeLivre);
		}
		System.out.println(listeLivre);
		
		
		em.close();
		emf.close();
		
		

	}

}
