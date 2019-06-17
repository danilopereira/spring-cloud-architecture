package de.smava.webapp.repository;

import de.smava.webapp.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {
}
