package com.example.bookmart.repository;

import com.example.bookmart.file.Lecture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LectureRepository extends MongoRepository<Lecture, String> {
}
