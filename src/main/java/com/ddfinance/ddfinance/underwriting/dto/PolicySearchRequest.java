package com.ddfinance.ddfinance.underwriting.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicySearchRequest {
    private String policyHolder;
    private String orderBy;
    private int pageNumber = 0;
    private int pageSize = 10;
}
