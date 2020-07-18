package eu.twino.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoanApplicationModel extends RepresentationModel<LoanApplicationModel> {

    @NonNull
    @Positive
    private BigDecimal amount;

    @NonNull
    @Positive
    private Integer termInDays;

    @NonNull
    private String personalId;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String ipAddress;
}
