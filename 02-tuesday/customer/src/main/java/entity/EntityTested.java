/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author root
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer cust1 = new Customer("Oscar", "nils");
        Customer cust2 = new Customer("Mads", "Brandt");
        try {
            em.getTransaction().begin();
            em.persist(cust1);
            em.persist(cust2);
            em.getTransaction().commit();
            //Verify that books are managed and has been given a database id
            System.out.println(cust1.getFirstName() + " " + cust1.getLastName());
            System.out.println(cust2.getFirstName() + " " + cust2.getLastName());

        } finally {
            em.close();
        }
    }

}
