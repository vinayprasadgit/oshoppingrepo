package com.techwave.cartRelation.service;

import com.techwave.cartRelation.EntityNotFoundException;
import com.techwave.cartRelation.config.ApiResponse;
import com.techwave.cartRelation.entity.Product;
import com.techwave.cartRelation.entity.User;
import com.techwave.cartRelation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) throws EntityNotFoundException{
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
        {
            return product.get();
        }
        else{
            throw new EntityNotFoundException(Product.class,"id",id.toString());
        }

    }

    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    public ResponseEntity<ApiResponse> deleteProduct(Integer id)  {
        try{
            productRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"success","Record deleted Sucessfully"),HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException ex)
        {
            throw new EntityNotFoundException(Product.class,"id",id.toString());
        }

    }
}
