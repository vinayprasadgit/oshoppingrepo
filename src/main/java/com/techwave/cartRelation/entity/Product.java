package com.techwave.cartRelation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    private String title;
    private String sku;
    private Integer rating;
    private String review;
    private Integer shop;
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "item_id")
//    @JsonIgnore
//    private Items items;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<ProductImage> productImageList;







}
