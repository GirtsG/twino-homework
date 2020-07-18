package eu.twino.homework.service;

import eu.twino.homework.domain.LoanApplicationCreationException;
import eu.twino.homework.domain.entity.*;
import eu.twino.homework.domain.repository.CountryRepository;
import eu.twino.homework.domain.repository.LoanApplicationRepository;
import eu.twino.homework.domain.repository.LoanRepository;
import eu.twino.homework.domain.repository.PersonRepository;
import eu.twino.homework.model.LoanApplicationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LoanApplicationService {

    private final IpApiService ipApiService;

    private final LoanApplicationRepository loanApplicationRepository;

    private final PersonRepository personRepository;

    private final CountryRepository countryRepository;

    private final LoanRepository loanRepository;

    @Value("${homework.loan.apply.max.per.time.count:10}")
    private int applyMaxCount;

    @Value("${homework.loan.apply.max.per.time.seconds:1}")
    private long applyMaxTime;

    @Transactional
    public LoanApplication createLoanApplication(LoanApplicationModel model) throws LoanApplicationCreationException {
        String countryCode = ipApiService.getCountryFromIp(model.getIpAddress());
        validateRequestForCountry(countryCode);
        validateRequestForPerson(model.getPersonalId());
        return loanApplicationRepository.save(createNewLoanApplication(model, countryCode));
    }

    private LoanApplication createNewLoanApplication(LoanApplicationModel model, String countryCode) {
        return new LoanApplication(
                model.getAmount(),
                model.getTermInDays(),
                LocalDateTime.now(),
                getOrCreateCountry(countryCode),
                model.getIpAddress(),
                createNewLoan(model)
        );
    }

    private Loan createNewLoan(LoanApplicationModel model) {
        return loanRepository.save(new Loan(
                model.getAmount(),
                model.getTermInDays(),
                LocalDate.now(),
                LocalDate.now().plusDays(model.getTermInDays()),
                LoanStatus.APPROVED,
                getOrCreatePerson(model)
        ));
    }

    private Person getOrCreatePerson(LoanApplicationModel model) {
        return personRepository.findByPersonalId(model.getPersonalId())
                .orElse(personRepository.save(
                        new Person(
                                model.getPersonalId(),
                                model.getName(),
                                model.getSurname(),
                                false)
                        )
                );
    }

    private Country getOrCreateCountry(String countryCode) {
        return countryRepository.findByCode(countryCode)
                .orElse(
                        countryRepository.save(new Country(countryCode))
                );
    }

    private void validateRequestForCountry(String countryCode) throws LoanApplicationCreationException {
        long count = loanApplicationRepository.countByCountryCodeAndApplyDateTimeAfter(
                countryCode,
                LocalDateTime.now().minusSeconds(applyMaxTime + 1)
        );
        if (count >= applyMaxCount) {
            throw new LoanApplicationCreationException(
                    format("Apply max time for country '%s' reached.", countryCode)
            );
        }
    }

    private void validateRequestForPerson(String personalId) {
        Optional<Person> maybePerson = personRepository.findByPersonalId(personalId);
        if (maybePerson.isPresent() && maybePerson.get().isBlacklisted()) {
            throw new LoanApplicationCreationException(
                    format("Person with personal id '%s' is blacklisted.", personalId)
            );
        }
    }
}
