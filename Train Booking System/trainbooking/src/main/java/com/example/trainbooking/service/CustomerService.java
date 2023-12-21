package com.example.trainbooking.service;

import com.example.trainbooking.entity.Customer;
import com.example.trainbooking.repository.CustomerRepository;
import com.example.trainbooking.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, LoginRepository loginRepository) {
        this.customerRepository = customerRepository;
        this.loginRepository = loginRepository;
    }

    public boolean registerCustomer(Customer customer) {
        customerRepository.save(customer);
        return true;
    }

    public boolean performLogin(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.map(l -> l.getPassword().equals(password)).orElse(false);
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        try {
            loginRepository.save(customer);
            return true;
        }
        catch (Exception e) {
            logger.error("An error occurred:", e);
            return false;
        }
    }
}
