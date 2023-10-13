package com.example.service;

import com.example.configuration.filters.JwtRequestFilter;
import com.example.entity.Cart;
import com.example.entity.Product;
import com.example.entity.UserEntity;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    public Product addNewProduct(Product product){
      return productRepository.save(product);

    }

    public List<Product> getAllProducts(int pageNumber, String searchKey){
        Pageable pageable = PageRequest.of(pageNumber , 6);
        if(searchKey.equals("")){
            return (List<Product>) productRepository.findAll(pageable);
        } else {
           return (List<Product>) productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }

    }

    public Product getProductDetailsById(Integer productId){
       return productRepository.findById(productId).get();
    }

    public void deleteProductDetails(Integer productId){
        productRepository.deleteById(productId);
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId){
        if(isSingleProductCheckout && productId !=0){
            //we are going to buy a single product
            List<Product> list = new ArrayList<>();
            Product product = productRepository.findById(productId).get();
            list.add(product);
            return list;
        }else{
            //we are going to check the entire cart
            String currentUser = JwtRequestFilter.CURRENT_USER;
            UserEntity user = userRepository.findByUsername(currentUser).get();
            List<Cart> carts = cartRepository.findByUserEntity(user);

            //fetch products of cart list and make a new list
            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());


        }
    }
}
