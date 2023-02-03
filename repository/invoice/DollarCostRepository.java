package com.oodles.repository.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.DollarCost;

@Repository
public interface DollarCostRepository extends JpaRepository<DollarCost, Long> {
	
	List<DollarCost> findAllByIsDeletedOrderByMonthAsc(boolean isDeleted);
	
	DollarCost findByMonthAndIsDeletedAndYear(int month, boolean isDeleted, int year);

	List<DollarCost> findAllByIsDeletedOrderByMonthAscYearAsc(boolean b);

	List<DollarCost> findAllByIsDeletedOrderByYearAsc(boolean b);

	List<DollarCost> findAllByIsDeletedOrderByYearAscMonthAsc(boolean b);

	List<DollarCost> findAllByYearAndIsDeletedOrderByYearDescMonthDesc(int year, boolean b);

	List<DollarCost> findAllByIsDeletedOrderByYearDescMonthDesc(boolean b);
	
}
