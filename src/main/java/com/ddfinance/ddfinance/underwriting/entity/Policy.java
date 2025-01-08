package com.ddfinance.ddfinance.underwriting.entity;


import com.ddfinance.ddfinance.core.entity.BaseEntity;
import com.ddfinance.ddfinance.underwriting.entity.enums.AstMarker;
import com.ddfinance.ddfinance.underwriting.entity.enums.PolicyStatus;
import com.ddfinance.ddfinance.underwriting.entity.enums.PolicyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "t_policies")
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
public class Policy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class",nullable = false)
    private String policyClass;

    private String policyNo;

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

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;


    private float rate = 0;

}
