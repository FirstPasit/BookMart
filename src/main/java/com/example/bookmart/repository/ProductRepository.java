package com.example.bookmart.repository;

import com.example.bookmart.file.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository <Product, String> {
    List<Product> findByFaculty(String faculty);
}
