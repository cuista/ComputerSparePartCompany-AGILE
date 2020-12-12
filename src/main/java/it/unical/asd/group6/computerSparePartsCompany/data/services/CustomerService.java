package it.unical.asd.group6.computerSparePartsCompany.data.services;

import it.unical.asd.group6.computerSparePartsCompany.data.entities.Customer;

public interface CustomerService {
    Customer registerNewCustomer(Customer customer);

    boolean checkLogin(String username, String password);
}
