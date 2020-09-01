/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.BankCustomer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
////import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

/**
 *
 * @author thomas
 */
public class FacadeBankCustomerTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final FacadeBankCustomer FE = FacadeBankCustomer.getFacadeBankCustomer(ENF);
    public FacadeBankCustomerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
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
    public void testGetCustomerByID() {
        int expectedId = 1;
        int resultId = FE.getCustomerByID(expectedId).getCustomerID();
        Assertions.assertEquals(expectedId, resultId);
    }
    
    @Test
    public void testGetCustomerByName(){
        String queryName = "alex";
        String expectedName = "alex sars";
        String resultName = FE.getCustomerByName(queryName).get(0).getFullName();
        Assertions.assertEquals(expectedName, resultName);
    }
    
    @Test
    public void testGetAllBankCustomers(){
        Assertions.assertTrue(!FE.getAllBankCustomers().isEmpty());
    }
    
    @Test
    public void testAddCustomer(){
        BankCustomer bc = new BankCustomer("test", "test", "accNumber", 300, 5, "whatever");
        int customersInDB = FE.getAllBankCustomers().size();
        int expectedCustomersInDB = customersInDB + 1;
        FE.addCustomer(bc);
        int resultCustomersInDB = FE.getAllBankCustomers().size();
        Assertions.assertEquals(expectedCustomersInDB, resultCustomersInDB);
    }
}
