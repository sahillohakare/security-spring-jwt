package com.sahil.security.controller;

import com.sahil.security.dto.AuthRequest;
import com.sahil.security.dto.Product;
import com.sahil.security.entity.UserInfo;
import com.sahil.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


    @PostMapping("/new")
    public  String addNewUser(@RequestBody UserInfo userInfo){
    return service.addUser(userInfo);
    }

    @PostMapping("/authenticate")
    public  String authenticateAndGetToken(@RequestBody AuthRequest authRequest){

    }
}
