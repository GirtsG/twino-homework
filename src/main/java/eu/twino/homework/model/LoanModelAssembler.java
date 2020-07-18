package eu.twino.homework.model;

import eu.twino.homework.controller.LoanController;
import eu.twino.homework.domain.entity.Loan;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class LoanModelAssembler extends RepresentationModelAssemblerSupport<Loan, LoanModel> {

    public LoanModelAssembler() {
        super(LoanController.class, LoanModel.class);
    }

    @Override
    public LoanModel toModel(Loan entity) {
        return new LoanModel(
                entity.getAmount(),
                entity.getTermInDays(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus().name(),
                entity.getPerson().getPersonalId(),
                entity.getPerson().getName(),
                entity.getPerson().getSurname()
        );
    }
}
