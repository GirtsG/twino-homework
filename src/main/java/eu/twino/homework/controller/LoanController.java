package eu.twino.homework.controller;

import eu.twino.homework.domain.entity.LoanStatus;
import eu.twino.homework.domain.repository.LoanRepository;
import eu.twino.homework.model.LoanModel;
import eu.twino.homework.model.LoanModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository repository;

    private final LoanModelAssembler assembler;

    @GetMapping("/list/approved")
    ResponseEntity<CollectionModel<LoanModel>> listAllApprovedLoans() {
        return ResponseEntity.ok(
                assembler.toCollectionModel(
                        repository.findByStatus(LoanStatus.APPROVED)
                )
        );
    }

    @GetMapping("/list/approved/person/{personalId}")
    ResponseEntity<?> listAllApprovedLoansByPerson(@NonNull @PathVariable String personalId) {
        return ResponseEntity.ok(
                assembler.toCollectionModel(
                        repository.findByStatusAndPersonPersonalId(
                                LoanStatus.APPROVED,
                                personalId
                        )
                )
        );
    }
}
