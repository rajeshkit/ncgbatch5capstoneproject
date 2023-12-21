package com.example.trainbooking.service;
import com.example.trainbooking.entity.Customer;
import com.example.trainbooking.repository.CustomerRepository;
import com.example.trainbooking.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
        @Mock
        private CustomerRepository customerRepository;

        @InjectMocks
        private CustomerService customerService;

        @Mock
        private LoginRepository loginRepository;

        @Test
        void testRegisterCustomer() {
            Customer customer = new Customer();

            Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

            boolean result = customerService.registerCustomer(customer);

            assertTrue(result);
        }

        @Test
        void testPerformLogin() {
            String email = "test@example.com";
            String password = "password";

            Customer customer = new Customer();
            customer.setEmail(email);
            customer.setPassword(password);

            Mockito.when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

            boolean result = customerService.performLogin(email, password);

            assertTrue(result);
        }

        @Test
        void testSaveCustomer_Success() {
            Customer customer = new Customer();

            Mockito.when(loginRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

            boolean result = customerService.saveCustomer(customer);

            assertTrue(result);
        }

        @Test
        void testSaveCustomer_Failure() {
            Customer customer = new Customer();

            Mockito.when(loginRepository.save(Mockito.any(Customer.class))).thenThrow(RuntimeException.class);

            boolean result = customerService.saveCustomer(customer);

            assertFalse(result);
        }
    }
