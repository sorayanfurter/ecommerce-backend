package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String orderFullName;
    private String orderFullAddress;
    private String orderContactNumber;
    private String orderAlternativeContactNumber;
    private String orderStatus;
    private Double orderAmount;
    @ManyToOne
    private Product product;
    @ManyToOne
    private UserEntity userEntity;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderFullName() {
        return orderFullName;
    }

    public String getOrderFullAddress() {
        return orderFullAddress;
    }

    public String getOrderContactNumber() {
        return orderContactNumber;
    }

    public String getOrderAlternativeContactNumber() {
        return orderAlternativeContactNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public Product getProduct() {
        return product;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }

    public void setOrderFullAddress(String orderFullAddress) {
        this.orderFullAddress = orderFullAddress;
    }

    public void setOrderContactNumber(String orderContactNumber) {
        this.orderContactNumber = orderContactNumber;
    }

    public void setOrderAlternativeContactNumber(String orderAlternativeContactNumber) {
        this.orderAlternativeContactNumber = orderAlternativeContactNumber;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public OrderDetail(String orderFullName, String orderFullAddress, String orderContactNumber, String orderAlternativeContactNumber, String orderStatus, Double orderAmount, Product product, UserEntity userEntity) {
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternativeContactNumber = orderAlternativeContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.userEntity = userEntity;
    }

    public OrderDetail(){

    }
}
