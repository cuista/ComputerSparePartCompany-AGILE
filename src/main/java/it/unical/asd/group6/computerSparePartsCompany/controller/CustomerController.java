package it.unical.asd.group6.computerSparePartsCompany.controller;

import it.unical.asd.group6.computerSparePartsCompany.core.exception.CustomerByUsernameNotFoundException;
import it.unical.asd.group6.computerSparePartsCompany.core.services.implemented.CustomerServiceImpl;
import it.unical.asd.group6.computerSparePartsCompany.data.dto.CustomerDTO;
import it.unical.asd.group6.computerSparePartsCompany.data.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("/login")
    public ResponseEntity<Boolean> doLogin(
            @RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(customerService.checkLogin(username,password));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> doSignUp (@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.registerNewCustomer(customer));
    }

    @PostMapping("/register-param")
    public ResponseEntity<Boolean> signUp (
            @RequestParam("name") String name, @RequestParam("surname") String surname,
            @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email,
            @RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("vatID") Long vatID) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setVATIdentificationNumber(vatID);
        System.out.println(customer);
        return ResponseEntity.ok(customerService.registerNewCustomer(customer));
    }

    @GetMapping("/user-check")
    public ResponseEntity<Boolean> checkUser(@RequestParam("username")String username) {
        return ResponseEntity.ok(customerService.searchByUsername(username));
    }

    @GetMapping("/email-check")
    public ResponseEntity<Boolean> checkEmail(@RequestParam("email")String email) {
        return ResponseEntity.ok(customerService.searchByEmail(email));
    }

    @GetMapping("/all-customers")
    public ResponseEntity<List<CustomerDTO>> allCustomers() {

        List<CustomerDTO> customers = customerService.getAllCustomer();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/stringtest")
    public ResponseEntity<String> stringtest() {
        return ResponseEntity.ok(String.format("this is a string"));
    }

    @GetMapping("/by-username")
    public ResponseEntity<CustomerDTO> getCustomerByUsername(String username) {

        CustomerDTO customer = customerService.getCustomerByUsername(username).orElseThrow(() -> new CustomerByUsernameNotFoundException(username));

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/del-customer")
    public ResponseEntity<Boolean> deleteCustomer(@RequestParam String username) {
        return ResponseEntity.ok(customerService.deleteCustomer(username));
    }

    @GetMapping("/report-totalpurchases")
    public ResponseEntity<Integer> getTotalPurchases(@RequestParam String username){
        return ResponseEntity.ok(customerService.getReportTotalPurchases(username));
    }

    @GetMapping("/report-totalamount")
    public ResponseEntity<Double> getTotalAmount(@RequestParam String username){
        return ResponseEntity.ok(customerService.getReportTotalAmountSpent(username));
    }

    @GetMapping("/report-favoritecategory")
    public ResponseEntity<String> getFavoriteCategory(@RequestParam String username){
        return ResponseEntity.ok(customerService.getReportFavoriteCategory(username));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestParam String username,@RequestParam String password,@RequestParam String oldPassword) {
        if(oldPassword.equals(customerService.getCustomerByUsername(username).get().getPassword()))
            return ResponseEntity.ok(customerService.updateCustomer(username,password));
        else
            return ResponseEntity.ok(false);
    }

    @PostMapping("/update-data")
    public ResponseEntity<Boolean> changeCustomerData(@RequestParam String username,@RequestParam String name,@RequestParam String surname, @RequestParam String phoneNumber,@RequestParam String iva) {
        return ResponseEntity.ok(customerService.updateDataCustomer(username,name,surname,phoneNumber,Long.parseLong(iva)));
    }

    @GetMapping("/all-usernames")
    public ResponseEntity<List<String>>getUsernames()
    {
        return ResponseEntity.ok(customerService.getAllUsernames());
    }

    @PutMapping("/{username}")
    public ResponseEntity<Customer> updateCustomerWithPut(@RequestBody Customer newCustomer, @PathVariable String username){

        Optional<Customer> optionalCustomer = customerService.getCustomerEntityByUsername(username);

        if (!optionalCustomer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = optionalCustomer.get();

        customer.setPassword(newCustomer.getPassword());
        customer.setVATIdentificationNumber(newCustomer.getVATIdentificationNumber());
        customer.setPhoneNumber(newCustomer.getPhoneNumber());
        customer.setName(newCustomer.getName());
        customer.setSurname(newCustomer.getSurname());
        customer.setEmail(newCustomer.getEmail());
        customerService.updateCustomerInfos(customer);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /*@PostMapping("/update-customer-infos")
    public ResponseEntity<Boolean> updateCustomerWithPost(@RequestParam){

    }*/
}
