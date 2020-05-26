package de.danilopereira.homework.loanapplications.config;

import de.danilopereira.homework.loanapplications.client.CustomerClient;
import de.danilopereira.homework.loanapplications.repository.LoanApplicationsRepository;
import de.danilopereira.homework.loanapplications.service.LoanApplicationsService;
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
