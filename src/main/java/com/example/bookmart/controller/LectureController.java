package com.example.bookmart.controller;

import com.example.bookmart.file.Lecture;
import com.example.bookmart.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LectureController {
    @Autowired
    private LectureRepository lectureRepo;

    @GetMapping("/lectures")
    public List<Lecture> getAllLrctures(){
        return lectureRepo.findAll();
    }

    @PostMapping("/lectures")
    public Lecture addLecture(@RequestBody Lecture lecture){
        return lectureRepo.save(lecture);
    }

    @DeleteMapping("/lectures/{id}")
    public ResponseEntity<?> deleteLecture(@PathVariable("id") String id){
        if (!lectureRepo.existsById(id)){
            return new ResponseEntity<>("Lecture not found", HttpStatus.NOT_FOUND);
        }
        lectureRepo.deleteById(id);
        return new ResponseEntity<>("Lecture deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/lectures/{id}")
    public ResponseEntity<?> updateLecture(@PathVariable("id") String id, @RequestBody Lecture lecture){
        Optional<Lecture> lectureData = lectureRepo.findById(id);

        if(lectureData.isPresent()){
            Lecture existingLecture = lectureData.get();
            existingLecture.setTitle(lecture.getTitle());
            existingLecture.setInstructor(lecture.getInstructor());
            existingLecture.setDescription(lecture.getDescription());
            existingLecture.setPrice(lecture.getPrice());
            existingLecture.setDate(lecture.getDate());
            lectureRepo.save(existingLecture);
            return new ResponseEntity<>(existingLecture, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Lecture not foubnd", HttpStatus.NOT_FOUND);
        }
    }
}
