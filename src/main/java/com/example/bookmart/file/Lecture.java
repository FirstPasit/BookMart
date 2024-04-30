package com.example.bookmart.file;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lectures")
public class Lecture {

    @Id
    private String id;
    private String title;
    private String owner;
    private String description;
    private String price;
    private String date;

    public Lecture(String id, String title, String owner, String description, String price, String date) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return owner;
    }

    public void setInstructor(String instructor) {
        this.owner = instructor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
