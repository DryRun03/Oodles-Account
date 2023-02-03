package com.oodles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.averagebilling.AverageBillingCompliance;

public interface AverageBillingComplianceRepository extends JpaRepository<AverageBillingCompliance, Long> {
	
	Optional<AverageBillingCompliance> findByProjectIdAndMonthAndYear(long projectId, int month, int year);
}
