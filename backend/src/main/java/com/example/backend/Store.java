package com.example.backend;

import java.util.HashMap;

// The store is just a simple in-memory hashmap
public class Store extends HashMap<Integer, Item> {
    // Using an integer as an ID for simplicity and URL readability
    private int idCounter = 0;

    public Store() {
        super();

        // Initialize the store with some items
        this.createItem(new Item("Apple", 0.50));
        this.createItem(new Item("Banana", 0.30));
        this.createItem(new Item("Orange", 0.60));
        this.createItem(new Item("Grapes", 2.00));
        this.createItem(new Item("Watermelon", 3.00));
    }

    public Item getItem(int id) {
        Item item = this.get(id);

        if (item == null) {
            throw new ItemNotFoundException(id);
        }

        return item;
    }

    public Item createItem(Item item) {
        idCounter++;
        return this.put(idCounter, item);
    }

    public Item updateItem(int id, Item item) {
        if (!this.containsKey(id)) {
            throw new ItemNotFoundException(id);
        }

        return this.put(id, item);
    }

    public Item removeItem(int id) {
        return this.remove(id);
    }
}
