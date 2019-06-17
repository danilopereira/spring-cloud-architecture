package de.smava.webapp.repository;

import de.smava.webapp.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, String> {
}
