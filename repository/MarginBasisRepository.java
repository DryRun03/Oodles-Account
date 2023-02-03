package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.Margin.MarginBasis;

public interface MarginBasisRepository extends JpaRepository<MarginBasis, Long>{

	MarginBasis findByMonthAndYear(int month, int year);

}
