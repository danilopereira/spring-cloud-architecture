package de.smava.homework.loanapplications.model;

import lombok.Data;

@Data
public class LoanApplicationsDTO {
    private Long id;
    private Double amount;
    private int duration;
    private String status;
}
