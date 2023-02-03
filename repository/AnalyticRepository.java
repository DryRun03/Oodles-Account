package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.Margin.AnalyticServer;

public interface AnalyticRepository extends JpaRepository<AnalyticServer, Long>{

	AnalyticServer findByMonthAndYearAndBuName(Integer month, Integer year, String buName);

	AnalyticServer findByBuName(String buName);

}
