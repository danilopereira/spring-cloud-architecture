package de.smava.homework.customer.service;

import de.smava.homework.customer.client.AuthClient;
import de.smava.homework.customer.entity.CustomerEntity;
import de.smava.homework.customer.exception.CustomerNotFoundException;
import de.smava.homework.customer.exception.UserNotFoundException;
import de.smava.homework.customer.model.CustomerDTO;
import de.smava.homework.customer.model.UserDTO;
import de.smava.homework.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthClient authClient;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        final Optional<UserDTO> user = authClient.findById(customerDTO.getUserId());

        if(!user.isPresent()){
            throw new UserNotFoundException();
        }

        customerDTO.setId(UUID.randomUUID().toString());
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerRepository.save(customerEntity);

        final CustomerDTO customerResponse = new CustomerDTO();
        customerResponse.setId(customerEntity.getId());
        return customerResponse;
    }

    public CustomerDTO findByCustomerId(Long id) {
        final Optional<CustomerEntity> customerEntity = customerRepository.findById(id);

        if(!customerEntity.isPresent()){
            throw new CustomerNotFoundException();
        }

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerEntity, customerDTO);

        return customerDTO;
    }
}
