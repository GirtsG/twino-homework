package eu.twino.homework.service;

import eu.twino.homework.domain.entity.LoanApplication;
import eu.twino.homework.domain.repository.CountryRepository;
import eu.twino.homework.domain.repository.LoanApplicationRepository;
import eu.twino.homework.domain.repository.LoanRepository;
import eu.twino.homework.domain.repository.PersonRepository;
import eu.twino.homework.model.LoanApplicationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
class LoanApplicationServiceTest {

    private LoanApplicationService service;

    @MockBean
    private IpApiService ipApiService;

    @MockBean
    private LoanApplicationRepository loanApplicationRepository;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private LoanRepository loanRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        this.service = new LoanApplicationService(
                ipApiService, loanApplicationRepository, personRepository, countryRepository, loanRepository
        );
    }

    @Test
    void shouldLoanApplicationBeSaved() {
        LoanApplicationModel model = createLoanApplicationModel();
        when(
                ipApiService.getCountryFromIp(anyString())
        ).thenReturn("LV");
        when(
                loanApplicationRepository.countByCountryCodeAndApplyDateTimeAfter(anyString(), any(LocalDateTime.class))
        ).thenReturn(-1L);
        when(
                personRepository.findByPersonalId(anyString())
        ).thenReturn(Optional.empty());

        service.createLoanApplication(model);

        verify(loanApplicationRepository, times(1)).save(any(LoanApplication.class));
    }

    private LoanApplicationModel createLoanApplicationModel() {
        return new LoanApplicationModel(
                BigDecimal.valueOf(100L),
                30,
                "asdf",
                "Juris",
                "Valdis",
                "randomip"
        );
    }
}