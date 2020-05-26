package de.danilopereira.homework.loanapplications.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "loan")
public class LoanEntity {

    @Id
    private String id;
    private String customerId;
    private Double amount;
    private int duration;
}
