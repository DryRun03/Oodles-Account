package com.oodles.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.ReserveSnapShot;

@Repository
public interface ReserveSnapShotRepository extends JpaRepository<ReserveSnapShot, Long> {

	List<ReserveSnapShot> findAllByBuNameAndMonthAndYearAndCreationDate(String businessVertical, int month, int year,
			Date dateToFind);

}
