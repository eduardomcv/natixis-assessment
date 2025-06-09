package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.Item;
import com.example.backend.services.StoreService;

import java.util.HashMap;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class StoreController {
    private final StoreService service;

    public StoreController() {
        this.service = new StoreService();
    }

    @GetMapping("/health-check")
    public String getHealthCheck() {
        return this.service.getHealthCheck();
    }

    @GetMapping("/store")
    public HashMap<Integer, Item> getService() {
        return this.service.getStore();
    }

    @PostMapping("/item")
    public Item createItem(@RequestBody @Validated Item item) {
        return this.service.createItem(item);
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable int id) {
        return this.service.getItem(id);
    }

    @PutMapping("item/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody @Validated Item item) {
        return this.service.updateItem(id, item);
    }

    @DeleteMapping("item/{id}")
    public Item deleteItem(@PathVariable int id) {
        return this.service.deleteItem(id);
    }
}
