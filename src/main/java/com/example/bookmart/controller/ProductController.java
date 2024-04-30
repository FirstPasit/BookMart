package com.example.bookmart.controller;

import com.example.bookmart.file.Product;
import com.example.bookmart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository prodrepo;

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return prodrepo.findAll();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return prodrepo.save(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id){
        if(!prodrepo.existsById(id)){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        prodrepo.deleteById(id);
            return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        Optional<Product> prodData = prodrepo.findById(id);

        if(prodData.isPresent()){
            Product changeProduct = prodData.get();
            changeProduct.setFullName(product.getFullName());
            changeProduct.setProdType(product.getProdType());
            changeProduct.setPrice(product.getPrice());
            prodrepo.save(changeProduct);
            return new ResponseEntity<>(changeProduct, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/products/faculty/{faculty}")
    public List<Product> getProductByFaculty(@PathVariable("faculty") String faculty){
        return prodrepo.findByFaculty(faculty);
    }
}
