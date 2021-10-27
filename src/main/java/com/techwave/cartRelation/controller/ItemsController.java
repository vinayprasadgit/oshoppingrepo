package com.techwave.cartRelation.controller;

import com.techwave.cartRelation.entity.Items;
import com.techwave.cartRelation.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/items")
public class ItemsController
{
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public List<Items> getAllItems()
    {
        return  itemService.getAllItems();
    }
    @GetMapping("/{Id}")
    public Items getItemById(@PathVariable("Id") Integer id)
    {
        return itemService.getItemById(id);
    }

    @PostMapping("/")
    public Items addItems(@RequestBody Items items)
    {
        return itemService.addItems(items);
    }

    @DeleteMapping("/{Id}")
    public String deleteItem(@PathVariable("Id") Integer id)
    {
        return itemService.deleteItems(id);
    }

}
