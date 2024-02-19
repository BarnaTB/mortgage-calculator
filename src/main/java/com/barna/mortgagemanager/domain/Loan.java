package com.barna.mortgagemanager.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {

    @Id
    private Long id;

    private byte years;

    private float annualInterestRate;

    private int principal;
}
