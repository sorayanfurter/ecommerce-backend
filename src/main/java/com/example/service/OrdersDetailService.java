package com.example.service;

import com.example.configuration.filters.JwtRequestFilter;
import com.example.entity.*;
import com.example.repository.CartRepository;
import com.example.repository.OrdersDetailRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersDetailService {

    private static final String ORDER_PLACED = "Placed";
    @Autowired
    private OrdersDetailRepository ordersDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;


    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckOut){
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for(OrderProductQuantity o: productQuantityList){
           Product product = productRepository.findById(o.getProductId()).get();

           String currentUser = JwtRequestFilter.CURRENT_USER;

           UserEntity user = userRepository.findByUsername(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternativeContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice()* o.getQuantity(),
                    product,
                    user
            );

            if(!isSingleProductCheckOut){
               List<Cart> carts = cartRepository.findByUserEntity(user);
               carts.stream().forEach(x -> cartRepository.deleteById(x.getCartId()));
            }

            ordersDetailRepository.save(orderDetail);
        }

    }

    public List<OrderDetail> getOrderDetails(){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        UserEntity user = userRepository.findByUsername(currentUser).get();
        return ordersDetailRepository.findByUserEntity(user);
    }

    public List<OrderDetail> getAllOrderDetails(String status){
        List<OrderDetail> orderDetails = new ArrayList<>();
        if(status.equals("All")){
        ordersDetailRepository.findAll().forEach(
                x -> orderDetails.add(x));}
        else{
            ordersDetailRepository.findByOrderStatus(status).forEach(
                    x-> orderDetails.add(x)
            );
        }
        return orderDetails;
    }

    public void markOrderAsDelivered(Integer orderId){
       OrderDetail orderDetail = ordersDetailRepository.findById(orderId).get();

       if(orderDetail != null){
           orderDetail.setOrderStatus("Delivered");
           ordersDetailRepository.save(orderDetail);
       }

    }
}
