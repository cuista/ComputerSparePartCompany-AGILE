package it.unical.asd.group6.computerSparePartsCompany.controller;

import it.unical.asd.group6.computerSparePartsCompany.core.services.ProductService;
import it.unical.asd.group6.computerSparePartsCompany.data.entities.Product;
import it.unical.asd.group6.computerSparePartsCompany.core.services.implemented.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> showAll(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/all-products/{brand}")
    public ResponseEntity<List<Product>> showAllByBrand(@PathVariable("brand") String brand){
        return ResponseEntity.ok(productService.getAllProductByBrand(brand));
    }

    @GetMapping("/all-products/{brand}/{model}")
    public ResponseEntity<List<Product>> showAllByBrandAndModel(@PathVariable("brand") String brand, @PathVariable("model") String model){
        return ResponseEntity.ok(productService.getAllProductByBrandAndModel(brand, model));
    }

    @GetMapping("/all-products/{price}")
    public ResponseEntity<List<Product>> showAllByByPriceIsLessThan(@PathVariable("price") Double price){
        return ResponseEntity.ok(productService.getAllProductByPriceIsLessThan(price));
    }

    @GetMapping("/all-products/{model}")
    public ResponseEntity<List<Product>> showAllByModel(@PathVariable("model") String model){
        return ResponseEntity.ok(productService.getProductsByModel(model));
    }

    @GetMapping("/all-products/{category}")
    public ResponseEntity<List<Product>> showAllByCategory(@PathVariable("category") String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/all-products/{min}/{max}")
    public ResponseEntity<List<Product>> showProductsByPriceRange(
            @PathVariable("min") Double min, @PathVariable("max") Double max) {
        return ResponseEntity.ok(productService.getProductsInPriceRange(min,max));
    }
}