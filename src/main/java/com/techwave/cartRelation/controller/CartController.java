package com.techwave.cartRelation.controller;

import com.techwave.cartRelation.entity.Cart;
import com.techwave.cartRelation.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    private CartService cartService;

    Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/")
    public List<Cart> getAllCarts()
    {
        logger.info("get All carts");
        return cartService.getAllCarts();
    }
    @GetMapping("/{Id}")
    public Cart getCartById(@PathVariable("Id") Integer id)
    {
        return cartService.getCartById(id);

    }
    @PostMapping("/")
    public Cart addcart(@RequestBody Cart cart)
    {
        return cartService.addCart(cart);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity deleteCart(@PathVariable("Id") Integer id)
    {
        return cartService.deleteCart(id);
    }
}
