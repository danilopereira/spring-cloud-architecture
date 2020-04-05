package de.smava.homework.customer.service;

import de.smava.homework.customer.client.AuthClient;
import de.smava.homework.customer.entity.CustomerEntity;
import de.smava.homework.customer.exception.UserNotFoundException;
import de.smava.homework.customer.model.CustomerRequestDTO;
import de.smava.homework.customer.model.CustomerResponseDTO;
import de.smava.homework.customer.model.UserDTO;
import de.smava.homework.customer.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private AuthClient authClient;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AuthClient authClient){
        this.customerRepository = customerRepository;
        this.authClient = authClient;
    }

    public Long createCustomer(CustomerRequestDTO customerRequestDTO) {

        final Optional<UserDTO> user = authClient.findById(customerRequestDTO.getUserId());

        if(!user.isPresent()){
            throw new UserNotFoundException();
        }

        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerRequestDTO, customerEntity);
        customerRepository.save(customerEntity);
        return null;
    }

    public CustomerResponseDTO findByCustomerId(Long id) {

        final Optional<CustomerEntity> customerEntity = customerRepository.findById(id);

        return null;
    }
}
