package com.ddfinance.ddfinance.underwriting.mapper;


import com.ddfinance.ddfinance.underwriting.dto.PolicyRequest;
import com.ddfinance.ddfinance.underwriting.dto.PolicyResponse;
import com.ddfinance.ddfinance.underwriting.entity.Policy;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PolicyModelMapper {


    public PolicyResponse mapPolicyToPolicyResponse(Policy policy) {
        PolicyResponse policyResponse = new PolicyResponse();
        policyResponse.setPolicyClass(policy.getPolicyClass());
        policyResponse.setId(policy.getId());
        policyResponse.setPolicyNo(policy.getPolicyNo());
        policyResponse.setPolicyHolder(policy.getPolicyHolder());
        policyResponse.setBranch(policy.getBranch());
        policyResponse.setAgent(policy.getAgent());
        policyResponse.setMarker(policy.getMarker());
        policyResponse.setPeriodFrom(policy.getPeriodFrom());
        policyResponse.setPeriodTo(policy.getPeriodTo());
        policyResponse.setInsuredAmount(policy.getInsuredAmount());
        policyResponse.setAnnualPremium(policy.getAnnualPremium());
        policyResponse.setEndorseAmount(policy.getEndorseAmount());
        policyResponse.setRiskNote(policy.getRiskNote());
        policyResponse.setPolicyType(policy.getPolicyType());
        policyResponse.setStatus(policy.getStatus());
        policyResponse.setRate(policy.getRate());
//        policyResponse.setCreatedAt(policy.getCreatedAt());
//        policyResponse.setUpdatedAt(policy.getUpdatedAt());
//        policyResponse.setCreatedBy(policy.getCreatedBy());
//        policyResponse.setUpdatedBy(policy.getUpdatedBy());

        return policyResponse;
    }

    public Map<String, Object> mapPolicyListToPolicyResponse(Page<Policy> policies) {
        List<PolicyResponse> policyResponses = policies.stream().map(
                this::mapPolicyToPolicyResponse
        ).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data",policyResponses);
        response.put("pageNumber",policies.getNumber());
        response.put("pageSize",policies.getNumberOfElements());
        response.put("totalElements",policies.getTotalElements());

        return response;
    }

    public Policy mapPolicyRequestToPolicy(PolicyRequest request) {
        Policy policy = new Policy();
        return getPolicy(request, policy);
    }

    public Policy mapPolicyRequestToUpdatePolicy(@Valid PolicyRequest request, Policy policy) {
        return getPolicy(request, policy);
    }

    private Policy getPolicy(@Valid PolicyRequest request, Policy policy) {
        policy.setPolicyClass(request.getPolicyClass());
        policy.setPolicyHolder(request.getPolicyHolder());
        policy.setBranch(request.getBranch());
        policy.setAgent(request.getAgent());
        policy.setMarker(request.getMarker());
        policy.setPeriodFrom(request.getPeriodFrom());
        policy.setPeriodTo(request.getPeriodTo());
        policy.setInsuredAmount(request.getInsuredAmount());
        policy.setAnnualPremium(request.getAnnualPremium());
        policy.setEndorseAmount(request.getEndorseAmount());
        policy.setRiskNote(request.getRiskNote());
        policy.setRate(request.getRate());
        return policy;
    }
}
