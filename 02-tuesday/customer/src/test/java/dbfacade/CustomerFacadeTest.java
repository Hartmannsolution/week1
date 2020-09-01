/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.tools.schemaframework.SchemaManager;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author root
 */
public class CustomerFacadeTest {

    EntityManagerFactory emf;
    CustomerFacade facade;
    EntityManager em;
    SchemaManager sm;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("pu");
        facade = new CustomerFacade(emf);
        facade.addCustomer("TEST1", "TEST1");
        facade.addCustomer("TEST2", "TEST2");
        facade.addCustomer("TEST3", "TEST3");
        facade.addCustomer("TEST4", "TEST4");
    }

    @After
    public void cleanUp() {
        em = emf.createEntityManager();
        sm = new SchemaManager(em.unwrap(DatabaseSession.class));
        sm.dropTable("CUSTOMER");
        emf.close();
    }

    @Test
    public void testFindByID() {
        long expectedID = 1l;
        Customer cust = facade.findByID(expectedID);
        long resultID = cust.getId();
        assertEquals(expectedID, resultID);
    }

    @Test
    public void testFindByLastName() {
        String expectedLastName = "TEST1";
        List<Customer> cust = facade.findByLastName(expectedLastName);
        String resultLastName = cust.get(0).getLastName();
        assertTrue(expectedLastName.equalsIgnoreCase(resultLastName));

    }

    @Test
    public void testGetNumberOfCustomers() {
        int expectedNumberOfCustomers = 4;
        int resultNumberOfCustomers = facade.getNumberOfCustomers();
        assertEquals(expectedNumberOfCustomers, resultNumberOfCustomers);
    }

    @Test
    public void testGetAllCustomers() {
        int expectedNumberOfCustomers = 4;
        int resultNumberOfCustomers = facade.getAllCustomers().size();
        assertEquals(expectedNumberOfCustomers, resultNumberOfCustomers);
    }
}
