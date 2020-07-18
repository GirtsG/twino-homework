package eu.twino.homework.controller;

import eu.twino.homework.model.LoanApplicationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RootController {

    private final H2ConsoleProperties h2ConsoleProperties;

    @GetMapping
    ResponseEntity<RepresentationModel> index() {
        String currentUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        RepresentationModel representationModel = new RepresentationModel();
        ServletUriComponentsBuilder.fromCurrentContextPath();
        representationModel.add(
                linkTo(methodOn(RootController.class).index())
                        .withSelfRel()
                        .withTitle("Application root")
        );
        representationModel.add(
                Link.of(currentUrl + h2ConsoleProperties.getPath())
                        .withRel(h2ConsoleProperties.getPath())
                        .withTitle("H2 Database console")
        );
        representationModel.add(linkTo(methodOn(LoanApplicationController.class).applyForLoan(new LoanApplicationModel()))
                .withRel("submit")
                .withTitle("Apply for New Loan")
        );
        representationModel.add(linkTo(methodOn(LoanController.class).listAllApprovedLoans())
                .withRel("list/approved/")
                .withTitle("List all approved Loans")
        );
        return ResponseEntity.ok(representationModel);
    }
}
