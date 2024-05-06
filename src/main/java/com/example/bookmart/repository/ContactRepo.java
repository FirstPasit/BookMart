package com.example.bookmart.repository;

import com.example.bookmart.file.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContactRepo extends MongoRepository<Products, String> {
    Optional<Products> findById(String id);
}
