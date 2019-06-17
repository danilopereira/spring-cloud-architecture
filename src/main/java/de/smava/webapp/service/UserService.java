package de.smava.webapp.service;

import de.smava.webapp.config.SecurityConstants;
import de.smava.webapp.entity.UserEntity;
import de.smava.webapp.exception.UserAlreadyFoundException;
import de.smava.webapp.model.UserDTO;
import de.smava.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        if (userRepository.findById(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyFoundException();
        }
        userDTO.setId(UUID.randomUUID().toString());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRole(SecurityConstants.ROLE_USER);
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);
        log.info("Saved user with id: {}", user.getId());
        userDTO.setPassword("*****");
        return userDTO;
    }
}
