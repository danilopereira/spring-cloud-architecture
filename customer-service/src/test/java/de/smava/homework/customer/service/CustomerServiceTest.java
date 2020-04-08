package de.smava.homework.customer.service;

import de.smava.homework.customer.CustomerServiceApplication;
import de.smava.homework.customer.client.AuthClient;
import de.smava.homework.customer.entity.CustomerEntity;
import de.smava.homework.customer.exception.CustomerNotFoundException;
import de.smava.homework.customer.exception.UserNotFoundException;
import de.smava.homework.customer.model.CustomerDTO;
import de.smava.homework.customer.model.UserDTO;
import de.smava.homework.customer.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceApplication.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private AuthClient authClient;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        when(authClient.findById(anyString())).thenReturn(mockUserDto());
        when(customerRepository.findById(anyString())).thenReturn(mockCustomerEntity());
    }

    @Test
    public void createCustomer() {
        final CustomerDTO customer = customerService.createCustomer(mockCustomerDTO());
        assertNotNull(customer.getId());
        assertEquals(mockCustomerDTO().getEmail(), customer.getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void createCustomerUserNotFound() {
        when(authClient.findById(anyString())).thenReturn(Optional.empty());
        customerService.createCustomer(mockCustomerDTO());
    }

    @Test
    public void findByCustomerId() {
        final CustomerDTO customer = customerService.findByCustomerId(UUID.randomUUID().toString());
        assertNotNull(customer.getId());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findByCustomerIdNotFound() {
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());
        customerService.findByCustomerId(UUID.randomUUID().toString());
    }

    private Optional<UserDTO> mockUserDto() {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId("1");
        userDTO.setUsername("test");
        userDTO.setPassword(UUID.randomUUID().toString());
        userDTO.setRoles("ADMIN");

        return Optional.of(userDTO);
    }

    private Optional<CustomerEntity> mockCustomerEntity() {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(UUID.randomUUID().toString());
        customerEntity.setEmail("test@email.com");
        customerEntity.setFirstName("Test");
        customerEntity.setLastName("Customer");
        customerEntity.setPhone("49 176 67890123");
        customerEntity.setUserId("1");

        return Optional.of(customerEntity);
    }

    private CustomerDTO mockCustomerDTO() {
        final CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(mockCustomerEntity().get(), customerDTO);
        customerDTO.setId(null);
        return customerDTO;
    }
}