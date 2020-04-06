package de.smava.homework.customer.service;

import de.smava.homework.customer.CustomerServiceApplication;
import de.smava.homework.customer.model.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceApplication.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createCustomer() {
        customerService.createCustomer(mockCustomerDTO());
    }

    @Test
    public void findByCustomerId() {
        final CustomerDTO customer = customerService.findByCustomerId(UUID.randomUUID().toString());
        assertNotNull(customer.getId());
    }

    private CustomerDTO mockCustomerDTO() {
        return null;
    }
}