package com.example.backend.models;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Item {
    @NonNull
    private String name;

    @NonNull
    private String price;

    private String imageName;

    public Item(String name, String price) {
        this.name = name;
        this.price = price;
        this.imageName = null; // Default to null if no image is provided
    }

    public Item(String name, String price, String imageName) {
        this.name = name;
        this.price = price;
        this.imageName = imageName;
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getImageURL() {
        if (this.imageName == null) {
            return null;
        }

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(imageName)
                .toUriString();
    }
}
