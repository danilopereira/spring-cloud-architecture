package de.smava.homework.loanapplications.client;

import de.smava.homework.loanapplications.model.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("${customer-service.name}")
public interface CustomerClient {

    @GetMapping("/api/customers/{id}")
    Optional<CustomerDTO> findById(@PathVariable Long id);
}
