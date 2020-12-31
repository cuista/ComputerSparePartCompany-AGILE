package it.unical.asd.group6.computerSparePartsCompany.controller;

import it.unical.asd.group6.computerSparePartsCompany.core.services.implemented.CustomerServiceImpl;
import it.unical.asd.group6.computerSparePartsCompany.core.services.implemented.ReviewServiceImpl;
import it.unical.asd.group6.computerSparePartsCompany.data.entities.Customer;
import it.unical.asd.group6.computerSparePartsCompany.data.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    @Autowired
    ReviewServiceImpl reviewService;

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("/prova")
    public ResponseEntity<Boolean>stampa()
    {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/byText")
    public ResponseEntity<Review>get(@RequestParam String text)
    {
        Review r = reviewService.getByTesto(text);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>>getAll()
    {
        return ResponseEntity.ok(reviewService.getAll());
    }

    @GetMapping("/all-by-customer")
    public ResponseEntity<List<Review>>getAllByCustomer(@RequestParam String username)
    {
        return ResponseEntity.ok(reviewService.getAllByCustomer(customerService.getCustomerByUsername(username).get()));
    }

    @GetMapping("/all-by-brand-and-model")
    public ResponseEntity<List<Review>>getAllByCustomer(@RequestParam String brand,@RequestParam String model)
    {
        return ResponseEntity.ok(reviewService.getAllByBrandAndModel(brand,model));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean>delete(@RequestParam String username,@RequestParam String title,@RequestParam String text,@RequestParam String brand,@RequestParam String model)
    {
        /*questo perchè non possono esistere due recensioni dello stesso utente che abbiamo stesso titolo e testo per uno stesso prodotto*/
        /*questo deve essere completato*/
        Customer c = customerService.getCustomerByUsername(username).get();
        Optional<Review>r = reviewService.getAllByCustomerAndTitleAndTextAndBrandAndModel(c,title,text,brand,model);
        if(r.isPresent())
        {
            reviewService.delete(r.get());
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    //@PostMapping("/add")

}
