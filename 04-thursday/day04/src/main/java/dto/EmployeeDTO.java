/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author root
 */
public class EmployeeDTO {
    long id;
    String name;
    String address;

    public EmployeeDTO(Employee emp) {
        this.id = emp.getId();
        this.name = emp.getName();
        this.address = emp.getAddress();
    }
    
    
    
}
