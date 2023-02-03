package com.oodles.Interfaces;

import java.util.List;
import java.util.Map;

import com.oodles.domain.ReserveSnapShot;

public interface ICompanyMarginService{

	Map<String, Object> getBuWiseInvoiceTotal( int month, String year);

	List<Object> getCompanywiseData(int month, int year);

	Map<String, Object> getDirectCostBuWise(int month, int year, List<Object> buWiseUsers,
			Map<String, Object> invoiceTotal);
	
	Map<String, Object> getDirectCostBuWiseV2(int month, int year, List<Object> buWiseUsers,
			Map<String, Object> invoiceTotal);
	
	Map<String, Object> getCompanyMargin(String accessToken, Map<String, Object> directCostTotal,
			Map<String, Object> invoiceTotal,int month,int year);

	void flushInvoicesCache();

	void flushDirectCostCache();

	void flushTeamData();

	Map<String, Object> getCompanyPL(String accessToken, int month, int year);

	Map<String, Object> getTotalCostDivision(String accessToken, int month, int year, String businessVertical, Map<String,Object> invoiceTotal);

	Map<String, Object> getIndirectCostDivision(String accessToken, int month, int year, Long projectId);

	Map<String, Object> getBuReserve(int month, int year, Map<String, Object> companyMargins, Map<String, Double> disputedAmount,Map<String, Object> invoiceTotal, Map<String, Object> directCostTotal, List<Object> buWiseUsers);

	Map<String, Double> getAverageDisputedPercentage(Integer year);

	List<ReserveSnapShot> buReserveCrone();

	Double getReimbursementCost(String accessToken, int month, int year);

}
