package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.ExpectedBillingRate;

public interface ExpectedBillingRepository extends JpaRepository<ExpectedBillingRate, Long>{

	ExpectedBillingRate findByMonthAndYearAndGrade(int month, int year, String grade);

	List<ExpectedBillingRate> findAllByMonthAndYear(int monthValue, int year);

}
