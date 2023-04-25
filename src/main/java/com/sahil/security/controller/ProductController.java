package com.sahil.security.controller;

import com.sahil.security.dto.AuthRequest;
import com.sahil.security.dto.Product;
import com.sahil.security.entity.UserInfo;
import com.sahil.security.service.JwtService;
import com.sahil.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ProductService service;

    @Autowired
    private AuthenticationManager authenticationManager;

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
    public  String authenticateAndGetToken(@RequestBody AuthRequest authRequest)
    {
        Authentication authentication=
                authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }else{
            throw  new UsernameNotFoundException("invalid user request");
        }


    }
}
