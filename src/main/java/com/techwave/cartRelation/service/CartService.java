package com.techwave.cartRelation.service;

import com.techwave.cartRelation.EntityNotFoundException;
import com.techwave.cartRelation.config.ApiResponse;
import com.techwave.cartRelation.entity.Cart;
import com.techwave.cartRelation.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Integer id)
    {
        return cartRepository.findById(id).get();
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public ResponseEntity<ApiResponse> deleteCart(Integer id) {
        cartRepository.deleteById(id);
        try{
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"Success","Record Deleted Successfully"),HttpStatus.OK);
    }catch (EntityNotFoundException ex)
        {
            throw new EntityNotFoundException(Cart.class,"id",id.toString());

        }
}

    public Cart getCartByName(Integer id) {
        return cartRepository.findById(id).get();
    }
}
