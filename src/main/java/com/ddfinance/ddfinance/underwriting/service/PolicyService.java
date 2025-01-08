package com.ddfinance.ddfinance.underwriting.service;


import com.ddfinance.ddfinance.config.ApplicationConstants;
import com.ddfinance.ddfinance.exceptions.ResourceNotFoundException;
import com.ddfinance.ddfinance.underwriting.dto.PolicyRequest;
import com.ddfinance.ddfinance.underwriting.dto.PolicySearchRequest;
import com.ddfinance.ddfinance.underwriting.entity.Policy;
import com.ddfinance.ddfinance.underwriting.entity.enums.PolicyStatus;
import com.ddfinance.ddfinance.underwriting.entity.enums.PolicyType;
import com.ddfinance.ddfinance.underwriting.mapper.PolicyModelMapper;
import com.ddfinance.ddfinance.underwriting.repository.PolicyRepository;
import com.ddfinance.ddfinance.utils.ApiResponse;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;

    private final PolicyModelMapper policyModelMapper;


    public ApiResponse fetchAllPolicies(PageRequest pageRequest){

        Page<Policy> policies = this.policyRepository.findAll(pageRequest);

        return ApiResponse.builder()
                .status(ApplicationConstants.SUCCESS_STATUS)
                .message("Success")
                .payload(this.policyModelMapper.mapPolicyListToPolicyResponse(policies))
                .build();
    }

    public ApiResponse create(PolicyRequest request) {

        Policy policy = this.policyModelMapper.mapPolicyRequestToPolicy(request);

        String policyNo = generatePolicyNumber(PolicyType.valueOf("POL"));
        policy.setPolicyNo(policyNo);
        policy.setStatus(PolicyStatus.ACTIVE);
        policy.setPolicyType(PolicyType.valueOf("POL")); // will read from the request in future

        Policy savedPolicy = this.policyRepository.save(policy);

        return ApiResponse.builder()
                .status(ApplicationConstants.SUCCESS_STATUS)
                .message("Success")
                .payload(this.policyModelMapper.mapPolicyToPolicyResponse(savedPolicy))
                .build();
    }


    private String generatePolicyNumber(PolicyType policyType) {

        // Fetch the current year
        int currentYear = LocalDate.now().getYear();


        long count = policyRepository.count();

        // Generate the type based on status
        int type = switch (policyType) {
            case POL -> 1;
            case END -> 2;
            case REN -> 3;
            case CNC -> 4;
        };

        // Format the policy number
        return String.format("POL/%06d/%d/%d", count + 1, currentYear, type);
    }

    public ApiResponse searchPolicy(@Valid PolicySearchRequest request, PageRequest pageRequest) {

        Specification<Policy> specification = (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction(); // Start with an empty conjunction (AND)

            if (request.getPolicyHolder() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("policyHolder"), "%" + request.getPolicyHolder() + "%"));
            }
            return predicate;
        };

        Page<Policy> policies = policyRepository.findAll(specification, pageRequest);

        return ApiResponse.builder()
                .status(ApplicationConstants.SUCCESS_STATUS)
                .message("Success")
                .payload(this.policyModelMapper.mapPolicyListToPolicyResponse(policies))
                .build();

    }

    public ApiResponse deletePolicy(Long policyId) {

        Optional<Policy> policy = this.policyRepository.findById(policyId);

        if (policy.isEmpty()) {
            throw new ResourceNotFoundException("Policy with given id not found");
        }

        this.policyRepository.delete(policy.get());

        return ApiResponse.builder()
                .status(ApplicationConstants.SUCCESS_STATUS)
                .message("Policy has been deleted")
                .payload(policyId)
                .build();
    }

    public ApiResponse fetchPolicyById(String policyId) {

        Optional<Policy> policy = this.policyRepository.findById(Long.valueOf(policyId));

        if (policy.isEmpty()) {
            throw new ResourceNotFoundException("Policy with given id not found");
        }

        return ApiResponse.builder()
                .status(ApplicationConstants.SUCCESS_STATUS)
                .message("Success")
                .payload(this.policyModelMapper.mapPolicyToPolicyResponse(policy.get()))
                .build();
    }

    public ApiResponse edit(@Valid PolicyRequest request, Long policyId) {

        Optional<Policy> policy = this.policyRepository.findById(policyId);

        if (policy.isEmpty()) {
            throw new ResourceNotFoundException("Policy with given id not found");
        }

        Policy updatedPolicy = this.policyModelMapper.mapPolicyRequestToUpdatePolicy(request,policy.get());

        this.policyRepository.save(updatedPolicy);

        return ApiResponse.builder()
                .status(ApplicationConstants.SUCCESS_STATUS)
                .message("Successfully updated Policy")
                .build();
    }
}
