package de.danilopereira.homework.loanapplications.repository;

import de.danilopereira.homework.loanapplications.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationsRepository extends JpaRepository<LoanEntity, String> {

    List<LoanEntity> findByCustomerId(String customerId);
}
