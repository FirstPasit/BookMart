package com.example.bookmart.repository;

import com.example.bookmart.file.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
