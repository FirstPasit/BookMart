package com.example.bookmart.controller;

import com.example.bookmart.file.Cover;
import com.example.bookmart.repository.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class CoverController {

    @Autowired
    private CoverRepository coverRepo;

    @PostMapping("/covers")
    public ResponseEntity<String> uploadCover(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }
        try {
            Cover cover = new Cover();
            cover.setName(file.getOriginalFilename());
            cover.setContentType(file.getContentType());
            cover.setData(file.getBytes());
            coverRepo.save(cover);
            return new ResponseEntity<>("Cover uploaded successfully",HttpStatus.OK);
        } catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading cover", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/covers/{id}")
    public ResponseEntity<String> updateCover(@PathVariable("id")String id, @RequestParam("file") MultipartFile file){
        Optional<Cover> coverData = coverRepo.findById(id);
        if(coverData.isPresent()){
            Cover cover = coverData.get();
            try {
                cover.setName(file.getOriginalFilename());
                cover.setContentType(file.getContentType());
                cover.setData(file.getBytes());
                coverRepo.save(cover);
                return new ResponseEntity<>("Cover updated successfully",HttpStatus.OK);
            } catch (IOException e){
                e.printStackTrace();
                return new ResponseEntity<>("Error updating cover",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>("Cover not found",HttpStatus.NOT_FOUND);
        }
    }
}
