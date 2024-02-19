package com.barna.mortgagemanager.services.impl;

import com.barna.mortgagemanager.domain.Loan;
import com.barna.mortgagemanager.domain.LoanEntity;
import com.barna.mortgagemanager.repositories.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static com.barna.mortgagemanager.TestData.testLoan;
import static com.barna.mortgagemanager.TestData.testLoanEntity;


@ExtendWith(MockitoExtension.class)
public class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl underTest;

    @Test
    public void testThatLoanIsSaved() {
        final Loan loan = testLoan();

        final LoanEntity loanEntity = testLoanEntity();

        when(loanRepository.save(eq(loanEntity))).thenReturn(loanEntity);

        final Loan result = underTest.create(loan);

        assertEquals(loan, result);
    }
}
