package de.smava.homework.customer.config;

import de.smava.homework.customer.client.AuthClient;
import de.smava.homework.customer.repository.CustomerRepository;
import de.smava.homework.customer.service.CustomerService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CustomerServiceTestConfiguration {

    @Bean
    public CustomerService customerService(){
        return new CustomerService(Mockito.mock(CustomerRepository.class), Mockito.mock(AuthClient.class));
    }
}
