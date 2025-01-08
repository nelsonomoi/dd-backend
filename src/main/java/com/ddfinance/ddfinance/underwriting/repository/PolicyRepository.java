package com.ddfinance.ddfinance.underwriting.repository;


import com.ddfinance.ddfinance.underwriting.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long>, JpaSpecificationExecutor<Policy> {
}
