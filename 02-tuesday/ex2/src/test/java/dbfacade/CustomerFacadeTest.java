package dbfacade;

import Entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerFacadeTest {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    private Customer c1 = new Customer("Brian", "North");
    private Customer c2 = new Customer("Alice", "Tower");

    public CustomerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Customer").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByID() {
        long id = c1.getId();
        long expResult = c1.getId();
        long result = facade.findByID(id).getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByLastName() {
        String name = "North";
        List<Customer> expResult = null;
        String expR = "North";
        List<Customer> cusList = facade.findByLastName(name);
        String result = cusList.get(0).getLastName();
        assertEquals(expR, result);

    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testGetNumberOfCustomers() {
        //CustomerFacade instance = null;
        int expResult = 2;
        int result = facade.getNumberOfCustomers();
        assertEquals(expResult, result);

    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAllCustomers() {
        CustomerFacade instance = null;
        int expResult = 2;
        List<Customer> result = facade.allCustomers();
        assertEquals(expResult, result.size());

    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAddCustomer() {
        Customer result = facade.addCustomer("Freya", "North");
        assertTrue(result != null);
    }

}
