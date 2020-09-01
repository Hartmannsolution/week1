package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeBankCustomer {

    private static FacadeBankCustomer instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeBankCustomer() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeBankCustomer getFacadeBankCustomer(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeBankCustomer();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(int id) {
        EntityManager em = emf.createEntityManager();
        CustomerDTO custDTO;
        try {
            custDTO = new CustomerDTO(em.find(BankCustomer.class, (long) id));
            return custDTO;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<CustomerDTO> DTOList = new ArrayList<>();
        try {
            TypedQuery<BankCustomer> tq = em.createQuery("SELECT b FROM BankCustomer b WHERE b.firstName = :name", BankCustomer.class);
            tq.setParameter("name", name);
            tq.getResultList().forEach(bk -> DTOList.add(new CustomerDTO(bk)));
            return DTOList;
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }

    }
    
    public List<BankCustomer> getAllBankCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> tq = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
            return tq.getResultList();
        } finally {
            em.close();
        }
    }
}
