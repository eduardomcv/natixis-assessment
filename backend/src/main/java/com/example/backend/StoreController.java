package com.example.backend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class StoreController {
    private final Store store;

    public StoreController() {
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

    @PostMapping("/item")
    public Item createItem(@RequestBody Item item) {
        return store.createItem(item);
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable int id) {
        return store.getItem(id);
    }

    @PutMapping("item/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item item) {
        return store.updateItem(id, item);
    }

    @DeleteMapping("item/{id}")
    public Item deleteItem(@PathVariable int id) {
        return store.deleteItem(id);
    }
}
