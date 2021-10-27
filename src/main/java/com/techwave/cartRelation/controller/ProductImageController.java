package com.techwave.cartRelation.controller;

import com.techwave.cartRelation.entity.ProductImage;
import com.techwave.cartRelation.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/productimage")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/all")
    public List<ProductImage> getAllProImage()
    {
        return productImageService.getAllProImage();
    }

    @DeleteMapping("/{Id}")
    public String deleteProImage(@PathVariable("Id") Integer id)
    {
        return productImageService.deleteProImage(id);
    }
}
