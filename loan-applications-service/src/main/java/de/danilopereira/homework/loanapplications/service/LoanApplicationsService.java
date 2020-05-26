package de.danilopereira.homework.loanapplications.service;

import de.danilopereira.homework.loanapplications.client.CustomerClient;
import de.danilopereira.homework.loanapplications.exception.CustomerNotFoundException;
import de.danilopereira.homework.loanapplications.exception.LoanNotFoundException;
import de.danilopereira.homework.loanapplications.model.CustomerClientDTO;
import de.danilopereira.homework.loanapplications.model.CustomerDTO;
import de.danilopereira.homework.loanapplications.model.LoanApplicationsDTO;
import de.danilopereira.homework.loanapplications.model.LoanApplicationsResponseDTO;
import de.danilopereira.homework.loanapplications.model.LoanIdDTO;
import de.danilopereira.homework.loanapplications.repository.LoanApplicationsRepository;
import de.danilopereira.homework.loanapplications.entity.LoanEntity;
import de.danilopereira.homework.loanapplications.model.LoanApplicationsRequestDTO;
import de.danilopereira.homework.loanapplications.utils.LoanStatusUtil;
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
        getCustomer(loanDTO.getCustomerId());

        loanDTO.setId(UUID.randomUUID().toString());

        final LoanEntity loanEntity = new LoanEntity();
        BeanUtils.copyProperties(loanDTO, loanEntity);

        loanApplicationsRepository.save(loanEntity);
        log.debug("Created loan application to the customer {}", loanDTO.getCustomerId());

        return new LoanIdDTO(loanEntity.getId());
    }

    public LoanApplicationsResponseDTO getLoanApplicationsByCustomerId(String customerId) {
        final CustomerClientDTO customer = getCustomer(customerId);

        final List<LoanEntity> loans = loanApplicationsRepository.findByCustomerId(customerId);
        if(loans == null || loans.isEmpty()){
            log.error("Don't exist loan applications to the customer {}", customer);
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

    private CustomerClientDTO getCustomer(String customerId) {
        final Optional<CustomerClientDTO> customer = customerClient.findById(customerId);

        if (!customer.isPresent()) {
            log.error("Customer {} not found!", customerId);
            throw new CustomerNotFoundException();
        }

        return customer.get();
    }
}
