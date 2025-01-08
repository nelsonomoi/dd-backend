package com.ddfinance.ddfinance.underwriting.dto;


import com.ddfinance.ddfinance.underwriting.entity.enums.AstMarker;
import com.ddfinance.ddfinance.underwriting.entity.enums.PolicyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PolicyRequest {

    @NotBlank
    private String policyClass;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    private String policyHolder;

    private String branch;

    private String agent;

    @Enumerated(EnumType.STRING)
    private AstMarker marker;

    private LocalDate periodFrom;

    private LocalDate periodTo;

    private double insuredAmount;

    private double annualPremium;

    private double endorseAmount;

    private String riskNote;


    private float rate = 0;
}
