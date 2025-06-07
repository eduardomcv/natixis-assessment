package com.example.backend;

import java.util.HashMap;

public class Store extends HashMap<Integer, Item> {
    public Store() {
        super();

        // Initialize the store with some items
        this.put(1, new Item("Apple", 0.50));
        this.put(2, new Item("Banana", 0.30));
        this.put(3, new Item("Orange", 0.60));
        this.put(4, new Item("Grapes", 2.00));
        this.put(5, new Item("Watermelon", 3.00));
    }
}
