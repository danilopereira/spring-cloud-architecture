package de.smava.homework.loanapplications.controller;

import de.smava.homework.loanapplications.model.LoanApplicationsRequestDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsResponseDTO;
import de.smava.homework.loanapplications.model.LoanIdDTO;
import de.smava.homework.loanapplications.service.LoanApplicationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/api/loanapplications")
public class LoanApplicationsController {

    private LoanApplicationsService loanApplicationsService;

    @PostMapping
    public ResponseEntity<LoanIdDTO> createLoanApplication(LoanApplicationsRequestDTO loanRequestDTO){
        return ResponseEntity.ok(loanApplicationsService.createLoanApplication(loanRequestDTO));
    }

    @GetMapping
    public ResponseEntity<LoanApplicationsResponseDTO> getLoanApplicationsByCustomerId(@RequestParam("customerId")
                                                                                           @NotNull Long customerId){
        return ResponseEntity.ok(loanApplicationsService.getLoanApplicationsByCustomerId(customerId));
    }
}
