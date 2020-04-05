package de.smava.homework.loanapplications.repository;

import de.smava.homework.loanapplications.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanApplicationsRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> findByCustomerId(Long customerId);
}
