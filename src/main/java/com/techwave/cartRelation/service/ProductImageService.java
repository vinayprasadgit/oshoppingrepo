package com.techwave.cartRelation.service;

import com.techwave.cartRelation.entity.ProductImage;
import com.techwave.cartRelation.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    public List<ProductImage> getAllProImage() {
        return productImageRepository.findAll();
    }

    public String deleteProImage(Integer id) {

       productImageRepository.deleteById(id);
        return "deleted Successfully";
    }
}
