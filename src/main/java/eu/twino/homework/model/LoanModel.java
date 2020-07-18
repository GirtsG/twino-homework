package eu.twino.homework.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class LoanModel extends RepresentationModel<LoanModel> {

    private BigDecimal amount;

    private Integer termInDays;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String personalId;

    private String personName;

    private String personSurname;
}
