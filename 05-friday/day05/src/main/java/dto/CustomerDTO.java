/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author root
 */
public class CustomerDTO {
    int customerID;
    String fullName;
    String accountNumber;
    double balance;

    public CustomerDTO(BankCustomer bankcust) {
        this.customerID = Integer.parseInt(bankcust.getId().toString());
        this.fullName = bankcust.getFirstName() + " " + bankcust.getLastName();
        this.accountNumber = bankcust.getAccountNumber();
        this.balance = bankcust.getBalance();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    
}
