package com.example.backend.models;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(int id) {
        super("Item with ID " + id + " not found.");
    }
}
