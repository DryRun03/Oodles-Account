package com.oodles.repository.payroll;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.AttendanceVerification;

public interface AttendanceVerificationRepository extends JpaRepository<AttendanceVerification, Long>{

	AttendanceVerification findAllByUserIdAndMonthAndYear(long userId, int month, int year);

	List<AttendanceVerification> findAllByMonthAndYearAndIsAttendanceVerified(int month, int year, boolean b);

	List<AttendanceVerification> findAllByMonthAndYear(int month, int year);
	
	AttendanceVerification findByUserIdAndMonthAndYear(long userId, int month, int year);

	AttendanceVerification findAllByUserIdAndMonthAndYearAndIsDeletedFalse(long userId, int month, int year);

	
	

}
