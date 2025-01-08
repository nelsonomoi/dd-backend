package com.ddfinance.ddfinance.underwriting.dto;


import com.ddfinance.ddfinance.underwriting.entity.enums.AstMarker;
import com.ddfinance.ddfinance.underwriting.entity.enums.PolicyStatus;
import com.ddfinance.ddfinance.underwriting.entity.enums.PolicyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PolicyResponse {
    private Long id;

    private String policyClass;

    private String policyNo;

    private String policyHolder;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

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

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    private float rate;
}
