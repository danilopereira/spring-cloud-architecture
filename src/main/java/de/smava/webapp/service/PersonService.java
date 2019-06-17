package de.smava.webapp.service;

import de.smava.webapp.entity.PersonEntity;
import de.smava.webapp.entity.UserEntity;
import de.smava.webapp.exception.UserNotFoundException;
import de.smava.webapp.model.PersonDTO;
import de.smava.webapp.model.UserDTO;
import de.smava.webapp.repository.PersonRepository;
import de.smava.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Transactional
    public PersonDTO create(PersonDTO personDTO, UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findById(userDTO.getId());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        PersonEntity person = new PersonEntity();
        BeanUtils.copyProperties(personDTO, person);
        person.setUser(optionalUser.get());
        personRepository.save(person);
        log.info("Saved person with id: {}", person.getId());
        return personDTO;
    }
}
