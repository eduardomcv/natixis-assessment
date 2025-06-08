package com.example.backend.models;

import org.springframework.lang.NonNull;

public class Item {
    @NonNull
    private String name;

    @NonNull
    private String price;

    public Item(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
