package com.example.repository;

import com.example.entity.Cart;
import com.example.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

    public List<Cart> findByUserEntity(UserEntity userEntity);

}
