package de.danilopereira.homework.loanapplications.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class LoanApplicationsResponseDTO {
    private CustomerDTO customer;
    private List<LoanApplicationsDTO> loans;
}
