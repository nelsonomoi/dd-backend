package com.ddfinance.ddfinance.utils;


import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseUtil {

    public static <T> Map<String, Object> pageableResponse(final Page<T> pageableResult){

        Map<String, Object> response = new HashMap<>();

        response.put("data",pageableResult.getContent());
        response.put("pageNumber",pageableResult.getNumber());
        response.put("pageSize",pageableResult.getNumberOfElements());
        response.put("totalElements",pageableResult.getTotalElements());

        return response;
    }


    public static <T> Map<String, Object> bodyResponse(final T bodyResponse){
        Map<String, Object> response = new HashMap<>();
        response.put("data",bodyResponse);
        return response;
    }
}
