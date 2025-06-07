package com.example.backend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class StoreController {
    private final Store store;

    public StoreController() {
        // The store is just a simple in-memory hashmap
        this.store = new Store();
    }

    @GetMapping("/health-check")
    public String getHealthCheck() {
        return "Store is up and running!";
    }

    @GetMapping("/store")
    public Store getStore() {
        return store;
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable int id) {
        Item item = store.get(id);

        if (item == null) {
            throw new ItemNotFoundException(id);
        }

        return item;
    }
}
