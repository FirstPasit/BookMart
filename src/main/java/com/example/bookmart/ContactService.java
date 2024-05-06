package com.example.bookmart;

import com.example.bookmart.file.Products;
import com.example.bookmart.repository.ContactRepo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional(rollbackOn = Exception.class)
public class ContactService {
    private final ContactRepo contactRepo;

    public ContactService(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public Page<Products> getAllProducts(int page, int size){
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Products getProduct(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Products createProduct(Products products) {
        return contactRepo.save(products);
    }

    public void deleteProduct(Products products) {
        // Assignment
    }


    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(Constant.PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/products/image/" + filename).toUriString();
        }catch (Exception exception) {
            throw new RuntimeException("Unable to save image");
        }
    };

}
