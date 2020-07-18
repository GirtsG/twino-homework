package eu.twino.homework.domain.repository;

import eu.twino.homework.domain.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    long countByCountryCodeAndApplyDateTimeAfter(String countryCode, LocalDateTime applyDateTime);
}
