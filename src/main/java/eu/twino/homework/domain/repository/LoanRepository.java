package eu.twino.homework.domain.repository;

import eu.twino.homework.domain.entity.Loan;
import eu.twino.homework.domain.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(LoanStatus status);

    List<Loan> findByStatusAndPersonPersonalId(LoanStatus status, String personalId);
}
