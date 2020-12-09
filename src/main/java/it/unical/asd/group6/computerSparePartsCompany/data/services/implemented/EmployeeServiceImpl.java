package it.unical.asd.group6.computerSparePartsCompany.data.services.implemented;

import it.unical.asd.group6.computerSparePartsCompany.data.dao.EmployeeDao;
import it.unical.asd.group6.computerSparePartsCompany.data.entities.Employee;
import it.unical.asd.group6.computerSparePartsCompany.data.exception.*;
import it.unical.asd.group6.computerSparePartsCompany.data.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    /*
    Ritorna TRUE se l'employee, con le rispettive credenziali, è presente nel db
     */
    @Override
    public boolean checkLogin(String username, String password) {
        Optional<Employee> opt = employeeDao.findByUsernameAndPassword(username, password);
        if(opt.isPresent())
            return true;
        else
            throw new EmployeeNotFoundException(username);
        return false;
    }
}
