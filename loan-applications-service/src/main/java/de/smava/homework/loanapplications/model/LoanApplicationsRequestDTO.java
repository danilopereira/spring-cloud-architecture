package de.smava.homework.loanapplications.model;

import lombok.Data;

@Data
public class LoanApplicationsRequestDTO {

    private Long customerId;
    private Double amount;
    private int duration;

}
