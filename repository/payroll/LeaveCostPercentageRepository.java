package com.oodles.repository.payroll;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.LeaveCostPercentage;

@Repository
public interface LeaveCostPercentageRepository extends JpaRepository<LeaveCostPercentage, Long> {
	
	List<LeaveCostPercentage> findAllByIsDeleted(boolean isdeleted);
}
