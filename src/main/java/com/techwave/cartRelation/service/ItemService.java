package com.techwave.cartRelation.service;

import com.techwave.cartRelation.entity.Items;
import com.techwave.cartRelation.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ItemService {

    @Autowired
    private ItemsRepository itemsRepository;

    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    public Items getItemById(Integer id) {

        return itemsRepository.findById(id).get();
    }

    public Items addItems(Items items) {
        return itemsRepository.save(items);
    }

    public String deleteItems(Integer id) {
        itemsRepository.deleteById(id);
        return "Record Deleted Successfully";
    }
}
