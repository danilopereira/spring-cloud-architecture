package de.smava.webapp.service;

import de.smava.webapp.entity.LoanEntity;
import de.smava.webapp.entity.UserEntity;
import de.smava.webapp.exception.UserNotFoundException;
import de.smava.webapp.model.LoanDTO;
import de.smava.webapp.model.LoanStatus;
import de.smava.webapp.model.UserDTO;
import de.smava.webapp.repository.LoanRepository;
import de.smava.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    @Transactional
    public LoanDTO create(LoanDTO loanDTO, UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findById(userDTO.getId());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        loanDTO.setId(UUID.randomUUID().toString());
        loanDTO.setStatus(LoanStatus.CREATED);
        LoanEntity loan = new LoanEntity();
        BeanUtils.copyProperties(loanDTO, loan);
        loan.setUser(optionalUser.get());
        loanRepository.save(loan);
        log.info("Saved loan with id: {}", loan.getId());
        return loanDTO;
    }
}
