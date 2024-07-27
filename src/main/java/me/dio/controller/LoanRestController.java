package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import me.dio.domain.model.Loan;
import me.dio.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/loans")
public class LoanRestController {

    private final LoanService loanService;

    public LoanRestController(LoanService loanService) {
        this.loanService = loanService;
    }



    //Get Methods
    @GetMapping
    @Operation(summary = "Get a list with all loans", method = "GET")
    public ResponseEntity<Iterable<Loan>> getAllLoans() {
        Iterable<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a loan by Id", method = "GET")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Loan loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    //Post Methods
    @PostMapping
    @Operation(summary = "Add a new loan on repository", method = "POST")
    public ResponseEntity<Loan> saveLoan(@RequestBody Loan newLoan) {
        var loanSaved = loanService.saveLoan(newLoan);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loanSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(loanSaved);
    }

    //Put Methods
    @PutMapping("/{id}")
    @Operation(summary = "Renew a loan (by Id) for 15 days from today", method = "PUT")
    public ResponseEntity<Loan> renewLoan (@PathVariable Long id) {
        var loanUpdated = loanService.renewLoan(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loanUpdated.getId())
                .toUri();
        return ResponseEntity.created(location).body(loanUpdated);
    }

    //Delete Methods
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a loan by Id", method = "DELETE")
    public ResponseEntity<Loan> deleteLoan(@PathVariable Long id) {
        var loanDeleted = loanService.deleteLoan(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loanDeleted.getId())
                .toUri();
        return ResponseEntity.created(location).body(loanDeleted);
    }
}
