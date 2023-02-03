package com.oodles.repository.payroll;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.Payslip;
import com.oodles.enums.PayslipStatus;

public interface PayslipRepository extends JpaRepository<Payslip, Long>{

//	Payslip findAllByUserIdAndMonthAndYearAndPayrollId(long userId, int month, int year, long payrollId);

	Payslip findAllByUserIdAndPayslipMonthAndPayslipYearAndPayrollId(long userId, int month, int year, long id);

	List<Payslip> findAllByUserId(long userId);

	List<Payslip> findAllByUserIdAndPayslipYearAndPayslipStatusAndIsArchived(long userId, int year, PayslipStatus status, boolean isArchive);

	Optional<Payslip> findByPayrollId(long payrollId);

	List<Payslip> findAllByUserIdAndPayslipYearAndPayslipStatusNotAndIsArchived(long userId, int parseInt,
			PayslipStatus saved, boolean b);

	List<Payslip> findAllByPayslipMonthAndPayslipYear(int month, int year);
}
