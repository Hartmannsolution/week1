/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author root
 */
public class MakeTestData {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer cust1 = new BankCustomer("alex", "sars", "2323", 34, 2, "yes");
        BankCustomer cust2 = new BankCustomer("alex", "sars", "2323", 34, 2, "yes");
        BankCustomer cust3 = new BankCustomer("alex", "sars", "2323", 34, 2, "yes");
        BankCustomer cust4 = new BankCustomer("alex", "sars", "2323", 34, 2, "yes");
        try {
            em.getTransaction().begin();
            em.persist(cust1);
            em.persist(cust2);
            em.persist(cust3);
            em.persist(cust4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
