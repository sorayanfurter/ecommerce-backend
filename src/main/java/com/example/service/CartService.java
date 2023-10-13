package com.example.service;

import com.example.configuration.filters.JwtRequestFilter;
import com.example.entity.Cart;
import com.example.entity.Product;
import com.example.entity.UserEntity;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public Cart addToCart(Integer productId){
        Product product = productRepository.findById(productId).get();
        String currentUser = JwtRequestFilter.CURRENT_USER;
        UserEntity user = userRepository.findByUsername(currentUser).get();

        List<Cart> cartList = cartRepository.findByUserEntity(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());

        if(filteredList.size()> 0){
            return null;
        }

        if(product != null && user != null){
         Cart cart = new Cart(product, user);
          return cartRepository.save(cart);
        }
      return null;
    }

    public List<Cart> getCartDetails(){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        UserEntity user = userRepository.findByUsername(currentUser).get();
        return cartRepository.findByUserEntity(user);
    }

    public void deleteCartItem(Integer cartId){
        cartRepository.deleteById(cartId);
    }
}
