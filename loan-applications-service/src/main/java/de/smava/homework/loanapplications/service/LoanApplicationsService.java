package de.smava.homework.loanapplications.service;

import de.smava.homework.loanapplications.client.CustomerClient;
import de.smava.homework.loanapplications.entity.LoanEntity;
import de.smava.homework.loanapplications.exception.CustomerNotFoundException;
import de.smava.homework.loanapplications.exception.LoanNotFoundException;
import de.smava.homework.loanapplications.model.CustomerDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsRequestDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsResponseDTO;
import de.smava.homework.loanapplications.repository.LoanApplicationsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LoanApplicationsService {

    private LoanApplicationsRepository loanApplicationsRepository;

    private CustomerClient customerClient;

    @Autowired
    public LoanApplicationsService(LoanApplicationsRepository loanApplicationsRepository,
                                   CustomerClient customerClient){
        this.loanApplicationsRepository = loanApplicationsRepository;
        this.customerClient = customerClient;
    }

    public Long createLoanApplication(LoanApplicationsRequestDTO loanDTO) {

        final Optional<CustomerDTO> customer = customerClient.findById(loanDTO.getCustomerId());

        if(!customer.isPresent()){
            throw new CustomerNotFoundException();
        }

        final LoanEntity loanEntity = new LoanEntity();
        BeanUtils.copyProperties(loanDTO, loanEntity);
        loanApplicationsRepository.save(loanEntity);
        return null;
    }

    public LoanApplicationsResponseDTO getLoanApplicationsByCustomerId(Long customerId) {

        //TODO validate customer

        final Optional<LoanEntity> loanEntity = loanApplicationsRepository.findByCustomerId(customerId);
        if(!loanEntity.isPresent()){
            throw new LoanNotFoundException();
        }
        LoanApplicationsResponseDTO loanApplicationsResponseDTO = new LoanApplicationsResponseDTO();

        return loanApplicationsResponseDTO;
    }
}
