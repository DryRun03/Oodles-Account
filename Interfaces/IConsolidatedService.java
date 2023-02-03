package com.oodles.Interfaces;

import java.util.List;
import java.util.Map;

import com.oodles.dto.AverageBillingRateDto;

public interface IConsolidatedService{

	Map<String,Object> getUserDataForConsolidatedPage(String accessToken, int month, int year, String userStatus, String businessVertical, List<Map<String, Object>> users);

	Map<String, Object> getSalaryDifference(String accessToken, int i, int year);

	List<Object> getUserSnapShot(String accessToken, Long projectId);

	List<Map<String, Object>> getUsersForSalaryReconcilliation(String accessToken, int month, int year, String userStatus,
			String businessVertical);

	void flushUsersCache();

	Map<String,Object> getIndirectCostForSalaryReconciliation(String accessToken, int i, int year, String userStatus,
			String businessVertical, List<Map<String,Object>> users);

	List<Object> getAverageBilling(String accessToken,AverageBillingRateDto averageBillingRateDto, String string);

	Boolean sendBillingComplianceMail(String accessToken, Long projectId, int month, int year);

	Boolean sendBillingRateMailToHeads(String accessToken, int month, int year, String businessVertical);

	Map<String,Object> getBillingComplianceProjectData(String accessToken, Long projectId, int month, int year);

}
