package com.oodles.repository.payroll;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oodles.domain.Payroll;
import com.oodles.domain.TimesheetCompVerification;
import com.oodles.enums.PayRollStatus;

public interface PayrollRepository extends JpaRepository<Payroll, Long>{

	Payroll findByMonthAndUserId(int month, long userId);

	Payroll findAllById(long payrollId);

	List<Payroll> findAllByMonthAndYear(int month, int year);

	Payroll findByMonthAndUserIdAndYear(int month, long userId, int year);

	Payroll findAllByMonthAndUserIdAndYear(int month, long userId, int year);

	List<Payroll> findAllByMonthAndYearAndPayRollStatusAndIsVerified(int month, int year, PayRollStatus verified,
			boolean b);

	List<Payroll> findAllByMonthAndYearAndPayRollStatus(int month, int year, PayRollStatus verified);

	List<Payroll> findAllByMonthAndYearAndIsAttendanceVerified(int month, int year, boolean b);

	Payroll findAllByMonthAndUserIdAndYearAndIsDeleted(int month, long userId, int year, boolean b);

	List<Payroll> findAllByMonthAndYearAndIsDeletedFalse(int month, int year);

	List<Payroll> findAllByMonthAndYearAndIsDeletedFalseAndIsPriority(int month, int year, boolean b);
	
	Payroll findAllByUserIdAndMonthAndYear(Long userId,int month, int year);
	
	Payroll findAllByMonthAndUserIdAndYearAndIsDeletedFalse(int month, long userId, int year);

	List<Payroll> findAllByMonthAndYearAndIsDeleted(int month, int year, boolean b);

	Payroll saveAndFlush(TimesheetCompVerification payroll);

	List<Payroll> findAllByMonthAndYearAndIsDeletedAndIsMarginIncludedFalse(int month, int year, boolean b);

}
