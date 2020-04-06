package de.smava.homework.loanapplications.config;

import de.smava.homework.loanapplications.client.CustomerClient;
import de.smava.homework.loanapplications.repository.LoanApplicationsRepository;
import de.smava.homework.loanapplications.service.LoanApplicationsService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class LoanApplicationsServiceTestConfig {

    @Bean
    public LoanApplicationsService loanApplicationsService(){
        return new LoanApplicationsService(Mockito.mock(LoanApplicationsRepository.class),
                Mockito.mock(CustomerClient.class));
    }
}
