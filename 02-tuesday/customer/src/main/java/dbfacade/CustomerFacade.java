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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author root
 */
public class CustomerFacade {

    private  EntityManagerFactory emf;

    private CustomerFacade() {
    }

    public CustomerFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Customer findByID(long id){
        EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String lastName){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lastName", Customer.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT COUNT(c) FROM Customer c");
            return Integer.parseInt(query.getSingleResult().toString());
        } finally {
            em.close();
        }
    }
    
    public List<Customer> getAllCustomers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Customer addCustomer(String firstName, String lastName){
        EntityManager em = emf.createEntityManager();
        Customer cust = new Customer(firstName, lastName);
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = new CustomerFacade(emf);
        
        Customer test1 = facade.findByID(2l);
        System.out.println("customer id: " + test1.getId());
        
        
        List<Customer> test2 = facade.findByLastName("nils");
        for (Customer customer : test2) {
            System.out.println("lastname: " + customer.getLastName());
        }
        
        System.out.println("Number of customers: " + facade.getNumberOfCustomers());
        
        List<Customer> allCustomers = facade.getAllCustomers();
        for (Customer allCustomer : allCustomers) {
            System.out.println("firstName: " + allCustomer.getFirstName());
        }
        
        facade.addCustomer("Alex", "Sars");
    }
}
