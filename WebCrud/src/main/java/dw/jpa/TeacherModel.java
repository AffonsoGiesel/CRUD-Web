package dw.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Data Access Object.
 */
public class TeacherModel {

	//"professores" é o nome da unidade de persistência no "persistence.xml".
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teachers");

	public static void include(String registration, String name) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Teacher teacher = new Teacher();
		teacher.setRegistration(registration);
		teacher.setName(name);

		// Grava o objeto no banco de dados.
		em.persist(teacher);
		em.getTransaction().commit();
		em.close();
	}

	public static void change(String registration, String name) {
	}

	public static void exclude(String registration) {
	}

	public static List<Teacher> list() {
		EntityManager em = emf.createEntityManager();
		//Não é SQL! É JPQL.
		String jpql = "from Teacher";
		TypedQuery<Teacher> query = em.createQuery(jpql, Teacher.class);
		List<Teacher> result = query.getResultList();
		em.close();
		return result;
	}
}
