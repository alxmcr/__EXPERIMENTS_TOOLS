package services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.entities.Person;
import com.example.services.PersonServiceImpl;

@Transactional
class PersonServiceImplTest {

	private EntityManagerFactory emf;
	private EntityManager em;

	@BeforeEach
	void setUp() {
		this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@AfterEach
	void tearDown() {
		emf.close();
	}

	/*
	 * @Test
	 * 
	 * @Transactional void savePersonRollback() { this.em =
	 * emf.createEntityManager();
	 * 
	 * // Operation PersonServiceImpl personService = new
	 * PersonServiceImpl(this.em);
	 * 
	 * // New person Person person = new Person(); person.setName("Michael");
	 * person.setLastname("Fisher");
	 * 
	 * Person pe = null; try { pe = personService.savePerson(person, true);
	 * assertNotNull(pe); assertTrue(pe.getId() > 0); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

	@Test
	@Transactional
	void testSavePersonDoWorkRollback() {
		this.em = emf.createEntityManager();

		// Operation
		PersonServiceImpl personService = new PersonServiceImpl(this.em);

		// New person
		Person person = new Person();
		person.setName("Michelle");
		person.setLastname("Rodriguez");

		Person pe = null;

		try {
			pe = personService.savePersonDoWork(person, true);
			System.out.println(pe);
			assertNotNull(pe);
			assertTrue(pe.getId() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}