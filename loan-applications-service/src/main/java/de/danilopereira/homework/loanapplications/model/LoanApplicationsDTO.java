package de.danilopereira.homework.loanapplications.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoanApplicationsDTO {
    private String id;
    private Double amount;
    private int duration;
    private String status;
}
