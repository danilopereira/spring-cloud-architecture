package de.danilopereira.homework.loanapplications.client;

import de.danilopereira.homework.loanapplications.model.CustomerClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("${customer-service.name}")
public interface CustomerClient {

    @GetMapping("/api/customers/{id}")
    Optional<CustomerClientDTO> findById(@PathVariable String id);
}
