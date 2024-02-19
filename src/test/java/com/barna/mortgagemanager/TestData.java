package com.barna.mortgagemanager;

import com.barna.mortgagemanager.domain.Loan;
import com.barna.mortgagemanager.domain.LoanEntity;

public final class  TestData {

    private TestData() {
    }

    public static Loan testLoan() {
        return Loan.builder()
                .annualInterestRate(3.5F)
                .years((byte) 5)
                .principal(30000000)
                .build();
    }

    public static LoanEntity testLoanEntity() {
        return LoanEntity.builder()
                .annualInterestRate(3.5F)
                .years((byte) 5)
                .principal(30000000)
                .build();
    }
}
