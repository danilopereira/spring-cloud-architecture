package de.smava.webapp.entity;

import de.smava.webapp.model.LoanStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "loan")
public class LoanEntity {
    @Id
    private String id;
    private Double amount;
    private Integer duration;
    private LoanStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
