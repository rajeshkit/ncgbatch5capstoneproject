package com.example.trainbooking.service;

import com.example.trainbooking.entity.Customer;

public interface ICustomerService {
    boolean registerCustomer(Customer customer);

    boolean performLogin(String email, String password);

    boolean saveCustomer(Customer customer);
}
