package com.example.controller;

import com.example.entity.Cart;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable(name = "productId") Integer productId){
        return cartService.addToCart(productId);

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getCartDetails")
    public List<Cart>  getCartDetails(){
     return cartService.getCartDetails();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping ("/deleteCartItem/{cartId}")
    public void deleteCartItem (@PathVariable(name = "cartId")Integer cartId){
     cartService.deleteCartItem(cartId);

    }
}
