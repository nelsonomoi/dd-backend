package com.ddfinance.ddfinance.utils;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ApiResponse {
    public String status;
    public String message;
    public Object payload;
}
