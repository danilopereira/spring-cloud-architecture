package de.smava.homework.loanapplications.controller;

import de.smava.homework.loanapplications.model.LoanApplicationsRequestDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsResponseDTO;
import de.smava.homework.loanapplications.model.LoanIdDTO;
import de.smava.homework.loanapplications.service.LoanApplicationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/api/loanapplications")
@RequiredArgsConstructor
public class LoanApplicationsController {

    private final LoanApplicationsService loanApplicationsService;

    @PostMapping
    public ResponseEntity<LoanIdDTO> createLoanApplication(@RequestBody LoanApplicationsRequestDTO loanRequestDTO) {
        return ResponseEntity.ok(loanApplicationsService.createLoanApplication(loanRequestDTO));
    }

    @GetMapping
    public ResponseEntity<LoanApplicationsResponseDTO> getLoanApplicationsByCustomerId(@RequestParam("customerId")
                                                                                       @NotNull String customerId) {
        return ResponseEntity.ok(loanApplicationsService.getLoanApplicationsByCustomerId(customerId));
    }
}
