package com.example.backend.models;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Item {
    @NonNull
    private String name;

    @NonNull
    private String price;

    @NonNull
    private String currency;

    private String imageName;

    public Item(String name, String price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.imageName = null; // Default to null if no image is provided
    }

    public Item(String name, String price, String currency, String imageName) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.imageName = imageName;
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
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
