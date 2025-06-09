package com.example.backend.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.example.backend.ItemNotFoundException;
import com.example.backend.models.Item;

@Service
public class StoreService {
    // The store is just a simple in-memory hashmap
    private HashMap<Integer, Item> store;
    // Using an integer as an ID for simplicity and URL readability
    private int idCounter;

    public StoreService() {
        this.store = new HashMap<>();
        this.idCounter = 0;

        // Initialize the store with some items
        this.createItem(new Item("Apple", "0.50", "EUR", "apple.jpg"));
        this.createItem(new Item("Banana", "0.30", "EUR", "banana.jpg"));
        this.createItem(new Item("Orange", "0.80", "EUR", "orange.jpg"));
        this.createItem(new Item("Grapes", "1.20", "EUR", "grapes.jpg"));
        this.createItem(new Item("Watermelon", "2.50", "EUR", "watermelon.jpg"));
    }

    public HashMap<Integer, Item> getStore() {
        return this.store;
    }

    public Item getItem(int id) {
        Item item = this.store.get(id);

        if (item == null) {
            throw new ItemNotFoundException(id);
        }

        return item;
    }

    public Item createItem(Item item) {
        idCounter++;

        return this.store.put(idCounter, item);
    }

    public Item updateItem(int id, Item item) {
        if (!this.store.containsKey(id)) {
            throw new ItemNotFoundException(id);
        }

        return this.store.put(id, item);
    }

    public Item deleteItem(int id) {
        return this.store.remove(id);
    }

    public String getHealthCheck() {
        return "Store is up and running!";
    }
}
