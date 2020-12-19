package it.unical.asd.group6.computerSparePartsCompany.core.services;

import it.unical.asd.group6.computerSparePartsCompany.data.entities.Customer;

public interface CustomerService {
    Boolean registerNewCustomer(Customer customer);

    Boolean checkLogin(String username, String password);
}
