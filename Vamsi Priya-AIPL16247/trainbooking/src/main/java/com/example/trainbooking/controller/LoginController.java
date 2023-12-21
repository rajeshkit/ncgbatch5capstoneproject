package com.example.trainbooking.controller;

import com.example.trainbooking.entity.Customer;
import com.example.trainbooking.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login-api")
public class LoginController {
    private final ICustomerService loginService;

    @Autowired
    public LoginController(ICustomerService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<String> authenticate(@RequestBody Customer customer) {
        boolean isAuthenticated = loginService.performLogin(customer.getEmail(), customer.getPassword());
        if (isAuthenticated) {
            loginService.saveCustomer(customer);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
