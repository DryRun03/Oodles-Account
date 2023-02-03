package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.averagebilling.BillingCompliancePercentage;

public interface BillingCompliancePercentageRepository extends JpaRepository<BillingCompliancePercentage, Long>{
	
	BillingCompliancePercentage findByIsArchive(Boolean isArchived);
}
