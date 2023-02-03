package com.oodles.Interfaces;

import java.util.List;
import java.util.Map;

import com.oodles.domain.ExpectedBillingRate;
/**
 * Payroll Trends Service.
 * @author Amit
 *
 */
import com.oodles.domain.averagebilling.AverageBillingCompliance;
public interface PayrollTrendsService {
	/**
	 * Calculates the Mean Salary, Minimum Salary, Max Salary and Median Salary of users.
	 * @param month
	 * @param year
	 * @param accessToken
	 * @return List<Object> containing salary trends.
	 */
	public List<Object> getPayrollTrends(String accessToken, int month, int year, String businessVerticle);
	
	Map<String, Object> getMinAndMaxSalaryOfGivenGrade(String grade, String accessToken);

	public ExpectedBillingRate saveExpectedBillingRate(String grade, String token, int month, int year, double billingRate);

	AverageBillingCompliance saveAverageBillingCompliance(int month, int year, String comments, long projectId);

}
