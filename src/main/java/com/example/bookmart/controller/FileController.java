package com.example.bookmart.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    @Value("${upload.dir}")
    private String uploadDir; // กำหนดโฟลเดอร์ที่ใช้ในการจัดเก็บไฟล์

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        // กำหนดตำแหน่งที่ไฟล์จะถูกบันทึก
        Path path = Paths.get(uploadDir + file.getOriginalFilename());

        try {
            // บันทึกไฟล์ลงในตำแหน่งที่กำหนด
            Files.copy(file.getInputStream(), path);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        // ตรวจสอบไฟล์ที่ต้องการดาวน์โหลด
        Path filePath = Paths.get(uploadDir + filename);
        if (!Files.exists(filePath)) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        try {
            // ส่งไฟล์กลับให้กับ client
            byte[] fileData = Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                    .body(fileData);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error downloading file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
