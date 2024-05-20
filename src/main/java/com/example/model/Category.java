package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "categories")
public class Category {
    @Id
    @JsonProperty("_id")
    private String id;

    private String name;

    // Constructor
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // Getter and Setter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
