package com.techwave.cartRelation.controller;

import com.techwave.cartRelation.EntityNotFoundException;
import com.techwave.cartRelation.entity.Product;
import com.techwave.cartRelation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }
    @GetMapping("/{Id}")
    public Product getProductById(@PathVariable("Id") Integer id)
    {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product)
    {
        return  productService.addProduct(product);
    }
    @DeleteMapping("/{Id}")
    public ResponseEntity deleteProduct(@PathVariable("Id") Integer id) throws EntityNotFoundException
    {
        return productService.deleteProduct(id);
    }

}
