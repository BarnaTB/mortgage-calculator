package com.barna.mortgagemanager.controllers;

import com.barna.mortgagemanager.TestData;
import com.barna.mortgagemanager.domain.Loan;
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

    @Test
    public void testLoanCreated() throws Exception {
        final Loan loan = TestData.testLoan();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String loanJson = objectMapper.writeValueAsString(loan);

        mockMvc.perform(MockMvcRequestBuilders.put("/loans"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(loanJson)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.annualInterestRate").value(loan.getAnnualInterestRate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.years").value(loan.getYears()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.principal").value(loan.getPrincipal()));
    }
}
