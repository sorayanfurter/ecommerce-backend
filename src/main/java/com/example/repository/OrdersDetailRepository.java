package com.example.repository;

import com.example.entity.OrderDetail;
import com.example.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersDetailRepository extends CrudRepository<OrderDetail, Integer> {

    public List<OrderDetail> findByUserEntity(UserEntity userEntity);

    public List<OrderDetail> findByOrderStatus(String status);
}
