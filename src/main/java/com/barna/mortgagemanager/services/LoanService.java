package com.barna.mortgagemanager.services;

import com.barna.mortgagemanager.domain.Loan;

import java.util.Optional;

public interface LoanService {
    Loan create(Loan loan);
    Optional<Loan> findById(String id);
}
