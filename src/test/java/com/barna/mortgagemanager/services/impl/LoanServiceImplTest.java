package com.barna.mortgagemanager.services.impl;

import com.barna.mortgagemanager.domain.Loan;
import com.barna.mortgagemanager.domain.LoanEntity;
import com.barna.mortgagemanager.repositories.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoLoan() {
        final String id = "123123123123";
        when(loanRepository.findById(eq(id)))
                .thenReturn(Optional.empty());

        final Optional<Loan> result = underTest.findById(id);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsLoanWhenOneExists() {
        final Loan loan = testLoan();
        final LoanEntity loanEntity = testLoanEntity();

        when(loanRepository.findById(String.valueOf(eq(Optional.ofNullable(loan.getId())))))
                .thenReturn(Optional.of(loanEntity));

        final Optional<Loan> result = underTest.findById(loan.getId());
        assertEquals(Optional.of(loan), result);
    }
}
