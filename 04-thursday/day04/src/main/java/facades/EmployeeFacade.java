/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author root
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeeById(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary(int topHighestSalaries) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary", Employee.class);
            query.setMaxResults(topHighestSalaries);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee createEmployeeWithParameters(String name, String address, double salary) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Employee emp = new Employee(name, address, salary);
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        } finally {
            em.close();
        }
    }

    public void createEmployeeWithObject(Employee emp) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade FE = EmployeeFacade.getEmployeeFacade(ENF);
        int numberOfEmployees = 10;
        String name = "Name";
        String address = "Address";
        double salary = 1000;
        while (numberOfEmployees
                > 0) {
            FE.createEmployeeWithParameters(name + numberOfEmployees, address + numberOfEmployees, salary * numberOfEmployees);
            numberOfEmployees--;
        }
    }

}
