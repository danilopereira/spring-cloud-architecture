package de.danilopereira.homework.customer.config;

import de.danilopereira.homework.customer.client.AuthClient;
import de.danilopereira.homework.customer.repository.CustomerRepository;
import de.danilopereira.homework.customer.service.CustomerService;
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
