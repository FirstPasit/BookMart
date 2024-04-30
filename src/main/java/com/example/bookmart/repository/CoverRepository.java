package com.example.bookmart.repository;

import com.example.bookmart.file.Cover;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoverRepository extends MongoRepository<Cover, String> {
}
