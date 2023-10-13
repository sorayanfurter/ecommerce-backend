package com.example.controller;

import com.example.entity.OrderDetail;
import com.example.entity.OrderInput;
import com.example.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersDetailController {
    @Autowired
    private OrdersDetailService ordersDetailService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/placeOrder/{isSingleProductCheckOut}")
    public void placeOrder(@PathVariable(name = "isSingleProductCheckOut") boolean isSingleProductCheckOut, @RequestBody OrderInput orderInput){
     ordersDetailService.placeOrder(orderInput, isSingleProductCheckOut);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getOrderDetails")

    public List<OrderDetail> getOrderDetails(){
     return ordersDetailService.getOrderDetails();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllOrderDetails/{status}")
    public List<OrderDetail> getAllOrderDetails(@PathVariable (name = "status") String status){
     return ordersDetailService.getAllOrderDetails(status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/markOrderAsDelivered/{orderId}")
    public void markOrderAsDelivered(@PathVariable(name = "orderId") Integer orderId){
      ordersDetailService.markOrderAsDelivered(orderId);
    }
}
