package eu.twino.homework.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoanApplication extends BaseEntity {

    private BigDecimal amount;

    private Integer termInDays;

    private LocalDateTime applyDateTime;

    @ManyToOne
    private Country country;

    private String ipAddress;

    @OneToOne
    private Loan loan;
}
