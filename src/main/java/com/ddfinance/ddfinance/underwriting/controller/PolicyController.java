package com.ddfinance.ddfinance.underwriting.controller;


import com.ddfinance.ddfinance.underwriting.dto.PolicyRequest;
import com.ddfinance.ddfinance.underwriting.dto.PolicySearchRequest;
import com.ddfinance.ddfinance.underwriting.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policy")
public class PolicyController {


    private final PolicyService policyService;

    @GetMapping("")
    public ResponseEntity<?> fetchAllLeases(@RequestParam(value = "pageNumber",defaultValue = "0") int pageNumber,
                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Sort sort = Sort.by(Sort.Order.desc("createdAt"));
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);
        return new ResponseEntity<>(this.policyService.fetchAllPolicies(pageRequest), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/{policyId}")
    public ResponseEntity<?> fetchPolicyById(@PathVariable("policyId") String policyId){
        return new ResponseEntity<>(this.policyService.fetchPolicyById(policyId),HttpStatusCode.valueOf(200));
    }


    @PostMapping("")
    public ResponseEntity<?> createPolicy(@Valid @RequestBody PolicyRequest request){
        return new ResponseEntity<>(this.policyService.create(request),HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<?> editPolicy(@Valid @RequestBody PolicyRequest request,@PathVariable("policyId") Long policyId){
        return new ResponseEntity<>(this.policyService.edit(request,policyId),HttpStatusCode.valueOf(200));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchPolicy(@Valid @RequestBody PolicySearchRequest request){
        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize());
        return new ResponseEntity<>(this.policyService.searchPolicy(request,pageRequest),HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<?> deletePolicy(@PathVariable("policyId") Long policyId){
        return new ResponseEntity<>(this.policyService.deletePolicy(policyId),HttpStatusCode.valueOf(200));
    }



}
