package com.example.trainbooking.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.trainbooking.entity.Customer;
import com.example.trainbooking.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
        private final ObjectMapper objectMapper = new ObjectMapper();

        @InjectMocks
        private CustomerController customerController;

        @Mock
        private ICustomerService customerService;

        @Test
        void testRegisterCustomer_Success() throws Exception {
            Customer customer = new Customer();
            customer.setCustomerName("John Doe");
            customer.setEmail("john@example.com");
            customer.setPassword("password");

            when(customerService.registerCustomer(customer)).thenReturn(true);

            MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

            mockMvc.perform(post("/customer-api/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(customer)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Customer registered successfully"));
        }

        @Test
        void testRegisterCustomer_Failure() throws Exception {
            Customer customer = new Customer();
            customer.setCustomerName("John Doe");
            customer.setEmail("john@example.com");
            customer.setPassword("password");

            when(customerService.registerCustomer(customer)).thenReturn(false);

            MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
            mockMvc.perform(post("/customer-api/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(customer)))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().string("Registration failed"));
        }
    }
