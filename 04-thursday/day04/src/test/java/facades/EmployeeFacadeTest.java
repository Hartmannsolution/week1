/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import javax.persistence.EntityManager;
import org.eclipse.persistence.tools.schemaframework.SchemaManager;
import org.eclipse.persistence.sessions.DatabaseSession;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author root
 */
public class EmployeeFacadeTest {

    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade FE = EmployeeFacade.getEmployeeFacade(ENF);
    private static final EntityManager EM = ENF.createEntityManager();
    private static final SchemaManager SM = new SchemaManager(EM.unwrap(DatabaseSession.class));

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        //Setup employees
        int numberOfEmployees = 10;
        String name = "Name";
        String address = "Address";
        double salary = 1000;
        while (numberOfEmployees > 0) {
            FE.createEmployeeWithParameters(name + numberOfEmployees, address + numberOfEmployees, salary * numberOfEmployees);
            numberOfEmployees--;
        }
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        SM.dropTable("employee");
        EM.close();
        ENF.close();
    }

    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test a method here.
     */
    @Test
    public void testgetEmployeeeById() {
        long expectedId = 1;
        long resultId = FE.getEmployeeeById(expectedId).getId();
        Assertions.assertEquals(expectedId, resultId);
    }
    
    @Test
    public void testgetEmployeesByName(){
        String expectedName = "Name1";
        String resultName = FE.getEmployeesByName(expectedName).get(0).getName();
        Assertions.assertEquals(expectedName, resultName);
    }
    
    @Test
    public void testgetAllEmployees(){
        int expectedNumberOfEmployees = 10;
        int resultNumberOfEmployees = FE.getAllEmployees().size();
        Assertions.assertEquals(expectedNumberOfEmployees, resultNumberOfEmployees);
    }
    
    @Test
    public void testgetEmployeesWithHighestSalary(){
        int expectedNumberOfEmployees = 3;
        int resultNumberOfEmployees = FE.getEmployeesWithHighestSalary(expectedNumberOfEmployees).size();
        Assertions.assertEquals(expectedNumberOfEmployees, resultNumberOfEmployees);
    }
    
    
}
