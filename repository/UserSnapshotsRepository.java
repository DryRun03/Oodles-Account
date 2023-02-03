package com.oodles.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.Margin.UserSnapshots;

public interface UserSnapshotsRepository extends JpaRepository<UserSnapshots, Long>{

	List<UserSnapshots> findAllByProjectIdAndMonthAndYearAndCreationDateAndUserId(Long projectId, int monthValue,
			int year, Date yesterday, Long userId);

}
