package com.barterx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.barterx.model.Item;
import com.barterx.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return service.addItem(item);
    }

    @GetMapping
    public List<Item> getItems() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable int id) {
        return service.getItemById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Item> getItemsByUser(@PathVariable int userId) {
        return service.getItemsByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        service.deleteItem(id);
    }
}