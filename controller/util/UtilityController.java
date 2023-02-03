package com.oodles.controller.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.Margin.BuReserve;
import com.oodles.domain.Margin.BuReserveDeductions;
import com.oodles.service.util.UtilityService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

/**
 * 
 * @author shivangi
 *
 */
@RestController
public class UtilityController {
	
	@Autowired
	UtilityService utilityService;

	/**
	 * Get Laptop Allowance of users owning their own assets
	 * @param accessToken
	 * @param userIds
	 * @return list of users Along with their L.A.
	 */
	@PreAuthorize("hasAnyRole('INFRA','INFRA_ADMIN','ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value= UrlMappings.GET_LAPTOP_ALLOWANCE)
	public ResponseEntity<Object> getLaptopAllowanceForAsset(@RequestHeader("Authorization") String accessToken,@RequestParam List<Long> userIds){
		List<Object> data = utilityService.getLaptopAllowanceForAsset(accessToken,userIds);
		if(!data.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.FETCHED_SUCCESSFULLY, data);
		}
		else 
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unable to fetch the data", data);
	}
	
	@PreAuthorize("(hasRole('ACCOUNTS') or (hasRole('ACCOUNTS_ADMIN'))) and hasRole('DASHBOARD_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value= UrlMappings.TOGGLE_SERVER)
	public ResponseEntity<Object> toggleServer(@RequestHeader("Authorization") String accessToken,@RequestParam String buName){
		Boolean data = utilityService.toggleServer(accessToken,buName);
		if(data) 
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_SERVER_CHANGED_TO_OLAP, data);
		else 
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_SERVER_CHANGED_TO_ACCOUNTS, data);
	}
	
	@RequestMapping(method = RequestMethod.GET, value= UrlMappings.USE_OLAP_SERVER)
	public ResponseEntity<Object> useAnalyticServer(@RequestHeader("Authorization") String accessToken,@RequestParam String buName){
		Boolean data = utilityService.useAnalyticServer(accessToken,buName);
		if(data) 
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.FETCHED_SUCCESSFULLY , data);
		else 
			return ResponseHandler.generateResponse(HttpStatus.OK, false, ConstantUtility.ACCESS_DENIED, data);
	}
	
	@GetMapping(UrlMappings.FETCH_INTERNAL_INVOICE)
	public ResponseEntity<Object> getInternalInvoice(@RequestHeader("Authorization") String accessToken,@RequestParam String businessVertical,
		@RequestParam String monthName,@RequestParam Map<String, Object> buMargin,@RequestParam String year,@RequestParam Double dollarexchangeCost){
		Map<String,Object > response = utilityService.getInternalInvoices(accessToken,businessVertical,monthName, year, dollarexchangeCost);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);
	}
	
	@GetMapping(UrlMappings.SYNC_INVOICE_OLAP_DATA)
	public ResponseEntity<Object> syncInvoiceOlapData(@RequestParam Integer month,@RequestParam Integer year){
		Map<String,Map<String,Double> > response = utilityService.syncOlapData(month,year);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);
	}

	@GetMapping(UrlMappings.SYNC_SALARY_OLAP_DATA)
	public ResponseEntity<Object> syncSalaryOlapData(@RequestParam Integer month,@RequestParam Integer year){
		Map<String,Double> response = utilityService.syncSalaryOlapData(month,year);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);
	}

	
	@GetMapping(UrlMappings.SYNC_RESERVE_DATA)
	public ResponseEntity<Object> syncReserveData(@RequestHeader("Authorization") String accessToken){
		List<BuReserve> response = utilityService.syncReserveData();
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);
	}
	
	@GetMapping(UrlMappings.SYNC_DEDUCTION_DATA)
	public ResponseEntity<Object> syncDeductionData(@RequestHeader("Authorization") String accessToken){
		List<BuReserveDeductions> response = utilityService.syncDeductionData();
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);
	}
	
	@GetMapping(UrlMappings.SYNC_IC_DATA)
	public ResponseEntity<Object> syncICData(@RequestParam Integer month,@RequestParam Integer year,@RequestParam String projectBusinessVertical){
		Map<String, Double> response = utilityService.syncICData(month,year,projectBusinessVertical);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);
	}

}
