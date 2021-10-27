package com.techwave.cartRelation.repository;

import com.techwave.cartRelation.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items,Integer>
{

}
