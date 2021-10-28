package com.techwave.cartRelation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
//Cart Entity
//before Revert cart
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private String fullname;
    private String mobile;
    private String email;
    private String line1;
    private String line2;
    private String city;
    private String state;
   @OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER,mappedBy="cart", orphanRemoval=true)
   @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Items> cart_items;


}
