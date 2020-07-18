package eu.twino.homework.model;

import eu.twino.homework.domain.entity.Loan;
import eu.twino.homework.domain.entity.LoanStatus;
import eu.twino.homework.domain.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LoanModelAssemblerTest {

    @Autowired
    private LoanModelAssembler assembler;

    @Test
    void shouldConvertToCorrectModel() {
        Loan entity = createEntity();

        LoanModel model = assembler.toModel(entity);

        assertEquals(entity.getAmount(), model.getAmount());
        assertEquals(entity.getTermInDays(), model.getTermInDays());
        assertEquals(entity.getStartDate(), model.getStartDate());
        assertEquals(entity.getEndDate(), model.getEndDate());
        assertEquals(entity.getStatus().name(), model.getStatus());
        assertEquals(entity.getPerson().getPersonalId(), model.getPersonalId());
        assertEquals(entity.getPerson().getName(), model.getPersonName());
        assertEquals(entity.getPerson().getSurname(), model.getPersonSurname());
    }

    private Loan createEntity() {
        return new Loan(
                BigDecimal.valueOf(345.67),
                57,
                LocalDate.now(),
                LocalDate.now().plusDays(57L),
                LoanStatus.APPROVED,
                new Person(
                        "randomPersonalId",
                        "randomName",
                        "randomSurname",
                        false
                )
        );
    }
}