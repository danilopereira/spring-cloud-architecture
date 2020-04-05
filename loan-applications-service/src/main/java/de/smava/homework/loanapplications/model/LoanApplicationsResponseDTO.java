package de.smava.homework.loanapplications.model;

import lombok.Data;

import java.util.List;

@Data
public class LoanApplicationsResponseDTO {
    private CustomerDTO customer;
    private List<LoanApplicationsDTO> loans;
}
