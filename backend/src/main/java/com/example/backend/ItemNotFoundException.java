package com.example.backend;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(int id) {
        super("Item with ID " + id + " not found.");
    }
}
