package eu.twino.homework.controller;

import eu.twino.homework.domain.LoanApplicationCreationException;
import eu.twino.homework.domain.entity.LoanApplication;
import eu.twino.homework.domain.repository.LoanApplicationRepository;
import eu.twino.homework.model.LoanApplicationModel;
import eu.twino.homework.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;

@RestController
@RequestMapping("/loan-application")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    private final LoanApplicationRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/submit")
    ResponseEntity<?> applyForLoan(@Validated @RequestBody LoanApplicationModel model) {
        try {
            LoanApplication loanApplication = loanApplicationService.createLoanApplication(model);
            return ResponseEntity
                    .created(
                            UriTemplate.of("/loan-application/{id}").expand(loanApplication.getId())
                    )
                    .body(EntityModel.of(loanApplication));
        } catch (LoanApplicationCreationException e) {
            logger.info(format("Apply for loan failed for request: %s", model), e);
            return ResponseEntity.ok(
                    Problem.create()
                            .withTitle("Apply for loan failed")
                            .withDetail(e.getMessage())
            );
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            Problem.create()
                                    .withTitle("Apply for loan failed")
                                    .withDetail(e.getMessage())
                    );
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getLoanApplication(@PathVariable Long id) {
        return ResponseEntity.ok(
                EntityModel.of(repository.findById(id))
        );
    }
}
