package de.danilopereira.homework.customer.service;

import de.danilopereira.homework.customer.client.AuthClient;
import de.danilopereira.homework.customer.entity.CustomerEntity;
import de.danilopereira.homework.customer.model.CustomerDTO;
import de.danilopereira.homework.customer.model.UserDTO;
import de.danilopereira.homework.customer.repository.CustomerRepository;
import de.danilopereira.homework.customer.exception.CustomerNotFoundException;
import de.danilopereira.homework.customer.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthClient authClient;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        final Optional<UserDTO> user = authClient.findById(customerDTO.getUserId());

        if(!user.isPresent()){
            log.error("User {} not found", customerDTO.getUserId());
            throw new UserNotFoundException();
        }

        customerDTO.setId(UUID.randomUUID().toString());
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerRepository.save(customerEntity);
        log.info("Saved customer with ID: {}", customerEntity.getId());
        return customerDTO;
    }

    public CustomerDTO findByCustomerId(String id) {
        final Optional<CustomerEntity> customerEntity = customerRepository.findById(id);

        if(!customerEntity.isPresent()){
            log.error("Customer {} not found", id);
            throw new CustomerNotFoundException();
        }

        log.debug("Customer {} found", customerEntity.get().toString());

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerEntity.get(), customerDTO);
        return customerDTO;
    }
}
