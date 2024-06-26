package com.example.bookmart.repository;

import com.example.bookmart.file.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository <Products, String> {
    List<Products> findByFaculty(String faculty);
}
