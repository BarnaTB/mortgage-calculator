package com.barna.mortgagemanager.controllers;

import com.barna.mortgagemanager.TestData;
import com.barna.mortgagemanager.domain.Loan;
import com.barna.mortgagemanager.services.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class LoanControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoanService loanService;

    @Test
    public void testLoanIsCreated() throws Exception {
        final Loan loan = TestData.testLoan();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String loanJson = objectMapper.writeValueAsString(loan);

        mockMvc.perform(MockMvcRequestBuilders.post("/loans")
                        .content(loanJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.annualInterestRate").value(loan.getAnnualInterestRate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.principal").value(loan.getPrincipal()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.years").value(loan.getYears()));
    }

    @Test
    public void testThatRetrieveLoanReturns404WhenNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/loans/123123123"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

//    @Test
//    public void testThatRetrieveLoanReturnsHttp200LoanWhenExists() throws Exception {
//        final Loan loan = TestData.testLoan();
//        loanService.create(loan);
//
//        System.out.println(loan.getId());
//        System.out.println(loan.getAnnualInterestRate());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/loans/" + loan.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
}
