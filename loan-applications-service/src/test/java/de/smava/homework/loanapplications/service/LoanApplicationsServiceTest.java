package de.smava.homework.loanapplications.service;

import de.smava.homework.loanapplications.client.CustomerClient;
import de.smava.homework.loanapplications.entity.LoanEntity;
import de.smava.homework.loanapplications.exception.CustomerNotFoundException;
import de.smava.homework.loanapplications.exception.LoanNotFoundException;
import de.smava.homework.loanapplications.model.CustomerClientDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsRequestDTO;
import de.smava.homework.loanapplications.model.LoanApplicationsResponseDTO;
import de.smava.homework.loanapplications.model.LoanIdDTO;
import de.smava.homework.loanapplications.repository.LoanApplicationsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanApplicationsServiceTest {

    @Autowired
    private LoanApplicationsService loanApplicationsService;

    @MockBean
    private CustomerClient customerClient;

    @MockBean
    private LoanApplicationsRepository loanApplicationsRepository;

    private final String customerId = "101";

    @Before
    public void setup(){
        when(loanApplicationsRepository.findByCustomerId(customerId)).thenReturn(mockLoanEntities(customerId));
        when(customerClient.findById(customerId)).thenReturn(mockCustomerClientDTO(customerId));
    }

    @Test
    public void createLoanApplication() {
        final LoanIdDTO loanApplication = loanApplicationsService.createLoanApplication(mockLoanApplicationRequestDTO(customerId));
        assertNotNull(loanApplication);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void createLoanApplicationCustomerNotFound() {
        when(customerClient.findById(customerId)).thenReturn(Optional.empty());
        loanApplicationsService.createLoanApplication(mockLoanApplicationRequestDTO(customerId));
    }

    @Test
    public void getLoanApplicationsByCustomerId() {
        final LoanApplicationsResponseDTO loanApplicationsByCustomerId = loanApplicationsService.getLoanApplicationsByCustomerId(customerId);
        assertNotNull(loanApplicationsByCustomerId);
        assertNotNull(loanApplicationsByCustomerId.getCustomer());
        assertFalse(loanApplicationsByCustomerId.getLoans().isEmpty());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void getLoanApplicationsByCustomerIdCustomerNofFound() {
        when(customerClient.findById(customerId)).thenReturn(Optional.empty());
        loanApplicationsService.getLoanApplicationsByCustomerId(customerId);
    }

    @Test(expected = LoanNotFoundException.class)
    public void getLoanApplicationsByCustomerIdLoanNofFound() {
        when(loanApplicationsRepository.findByCustomerId(customerId)).thenReturn(null);
        loanApplicationsService.getLoanApplicationsByCustomerId(customerId);
    }

    private LoanApplicationsRequestDTO mockLoanApplicationRequestDTO(String customerId) {
        final LoanApplicationsRequestDTO loanApplicationsRequestDTO = new LoanApplicationsRequestDTO();
        loanApplicationsRequestDTO.setAmount(1000.00);
        loanApplicationsRequestDTO.setCustomerId(customerId);
        loanApplicationsRequestDTO.setDuration(12);

        return loanApplicationsRequestDTO;
    }

    private List<LoanEntity> mockLoanEntities(String customerId) {
        final LoanEntity loanEntity = new LoanEntity();

        loanEntity.setId(UUID.randomUUID().toString());
        loanEntity.setAmount(120.00);
        loanEntity.setCustomerId(customerId);
        loanEntity.setDuration(24);

        return Collections.singletonList(loanEntity);
    }

    private Optional<CustomerClientDTO> mockCustomerClientDTO(String customerId) {
        final CustomerClientDTO customerClientDTO = new CustomerClientDTO();

        customerClientDTO.setId(customerId);
        customerClientDTO.setUserId("1");
        customerClientDTO.setFirstName("Test");
        customerClientDTO.setLastName("Customer");
        customerClientDTO.setEmail("test@email.com");
        customerClientDTO.setPhone("49 176 67890123");

        return Optional.of(customerClientDTO);
    }
}