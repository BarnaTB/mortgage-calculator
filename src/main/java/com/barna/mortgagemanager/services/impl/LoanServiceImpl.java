package com.barna.mortgagemanager.services.impl;

import com.barna.mortgagemanager.domain.Loan;
import com.barna.mortgagemanager.domain.LoanEntity;
import com.barna.mortgagemanager.repositories.LoanRepository;
import com.barna.mortgagemanager.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(final LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan create(final Loan loan) {
        final LoanEntity loanEntity = loanToLoanEntity(loan);
        final LoanEntity savedLoanEntity = loanRepository.save(loanEntity);

        return loanEntityToLoan(savedLoanEntity);
    }

    private LoanEntity loanToLoanEntity(Loan loan) {
        // Convert Loan to a Loan Entity
        return LoanEntity.builder()
                .annualInterestRate(loan.getAnnualInterestRate())
                .years(loan.getYears())
                .principal(loan.getPrincipal())
                .build();
    }

    private Loan loanEntityToLoan(LoanEntity loanEntity) {
        // Convert Loan Entity to a Loan
        return Loan.builder()
                .annualInterestRate(loanEntity.getAnnualInterestRate())
                .years(loanEntity.getYears())
                .principal(loanEntity.getPrincipal())
                .build();
    }
}
