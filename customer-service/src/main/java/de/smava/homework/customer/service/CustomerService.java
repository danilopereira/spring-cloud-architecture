package de.smava.homework.customer.service;

import de.smava.homework.customer.client.AuthClient;
import de.smava.homework.customer.entity.CustomerEntity;
import de.smava.homework.customer.exception.CustomerNotFoundException;
import de.smava.homework.customer.exception.UserNotFoundException;
import de.smava.homework.customer.model.CustomerDTO;
import de.smava.homework.customer.model.UserDTO;
import de.smava.homework.customer.repository.CustomerRepository;
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

        final Optional<UserDTO> user = authClient.findById(customerDTO.getUserId(), customerDTO.getToken());

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

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerEntity.get(), customerDTO);
        return customerDTO;
    }
}
