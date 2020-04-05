package de.smava.homework.customer.controller;

import de.smava.homework.customer.model.CustomerRequestDTO;
import de.smava.homework.customer.model.CustomerResponseDTO;
import de.smava.homework.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Long> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        return ResponseEntity.ok(customerService.createCustomer(customerRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findByCustomerId(id));
    }
}
