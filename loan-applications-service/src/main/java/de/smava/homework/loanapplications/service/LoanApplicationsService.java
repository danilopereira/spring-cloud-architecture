package de.smava.homework.loanapplications.service;

import de.smava.homework.loanapplications.client.CustomerClient;
import de.smava.homework.loanapplications.entity.LoanEntity;
import de.smava.homework.loanapplications.exception.CustomerNotFoundException;
import de.smava.homework.loanapplications.exception.LoanNotFoundException;
import de.smava.homework.loanapplications.model.CustomerClientDTO;
import de.smava.homework.loanapplications.model.CustomerDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsRequestDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsResponseDTO;
import de.smava.homework.loanapplications.model.LoanIdDTO;
import de.smava.homework.loanapplications.repository.LoanApplicationsRepository;
import de.smava.homework.loanapplications.utils.LoanStatusUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanApplicationsService {

    private final LoanApplicationsRepository loanApplicationsRepository;
    private final CustomerClient customerClient;

    public LoanIdDTO createLoanApplication(LoanApplicationsRequestDTO loanDTO) {
        getCustomer(loanDTO.getCustomerId(), loanDTO.getToken());

        loanDTO.setId(UUID.randomUUID().toString());

        final LoanEntity loanEntity = new LoanEntity();
        BeanUtils.copyProperties(loanDTO, loanEntity);
        loanApplicationsRepository.save(loanEntity);

        return new LoanIdDTO(loanEntity.getId());
    }

    public LoanApplicationsResponseDTO getLoanApplicationsByCustomerId(String customerId, String token) {
        final CustomerClientDTO customer = getCustomer(customerId, token);

        final List<LoanEntity> loans = loanApplicationsRepository.findByCustomerId(customerId);
        if(loans == null || loans.isEmpty()){
            throw new LoanNotFoundException();
        }

        return generateLoanApplicationsResponseDTO(customer, loans);
    }

    private LoanApplicationsResponseDTO generateLoanApplicationsResponseDTO(CustomerClientDTO customer, List<LoanEntity> loans) {
        final CustomerDTO customerDTO = new CustomerDTO(customer);
        final List<LoanApplicationsDTO> loansDTO = loans.stream().map(loanEntity -> LoanApplicationsDTO.builder()
                .id(loanEntity.getId())
                .amount(loanEntity.getAmount())
                .duration(loanEntity.getDuration())
                .status(LoanStatusUtil.randomLoanStatus().toString())
                .build()).collect(Collectors.toList());

        return LoanApplicationsResponseDTO.builder()
                .customer(customerDTO)
                .loans(loansDTO)
                .build();
    }

    private CustomerClientDTO getCustomer(String customerId, String token) {
        final Optional<CustomerClientDTO> customer = customerClient.findById(customerId, token);

        if (!customer.isPresent()) {
            throw new CustomerNotFoundException();
        }

        return customer.get();
    }
}
