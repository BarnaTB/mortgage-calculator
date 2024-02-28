package com.barna.mortgagemanager.controllers;

import com.barna.mortgagemanager.domain.Loan;
import com.barna.mortgagemanager.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(path="/loans")
    public ResponseEntity<?> createLoan(@RequestBody final Loan loan) {
        try {
            final Loan savedLoan = loanService.create(loan);
            return new ResponseEntity<Loan>(savedLoan, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<Loan> retrieveLoan(@PathVariable final String id) {
        final Optional<Loan> foundLoan = loanService.findById(id);
        return foundLoan.map(loan -> new ResponseEntity<Loan>(loan, HttpStatus.OK))
                .orElse(new ResponseEntity<Loan> (HttpStatus.NOT_FOUND));
    }

    static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
