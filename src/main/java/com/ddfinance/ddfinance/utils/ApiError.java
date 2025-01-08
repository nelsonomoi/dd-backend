package com.ddfinance.ddfinance.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class ApiError {

    private String status;

    private String message;

    private List<Object> errors = new ArrayList<>();


    public ApiError(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiError(String status, String message, List<Object> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
