package com.oodles.controller.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.Interfaces.IConsolidatedService;
import com.oodles.service.AccessUtility.AccessUtilityService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;
	
@RestController
public class ConsolidatedController {
	
	@Autowired
	IConsolidatedService consolidatedService;
	
	@Autowired
	AccessUtilityService utilityService;
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_SALARY_RECONCILIATION)
	public ResponseEntity<Object> getUserDataForConsolidatedPage(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year,@RequestParam String userStatus,@RequestParam(required = false) String businessVertical){
		List<Map<String, Object>> users=consolidatedService.getUsersForSalaryReconcilliation(accessToken,month+1,year,userStatus,businessVertical);
		Map<String,Object> data=consolidatedService.getUserDataForConsolidatedPage(accessToken,month+1,year,userStatus,businessVertical,users);
		if(data==null && data.isEmpty())
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unable to fetch data", data);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY, data);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_USERS_SALARY_RECONCILIATION)
	public ResponseEntity<Object> getUsersForSalaryReconcilliation(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year,@RequestParam String userStatus,@RequestParam(required = false) String businessVertical){
		consolidatedService.flushUsersCache();
		List<Map<String, Object>> data=consolidatedService.getUsersForSalaryReconcilliation(accessToken,month+1,year,userStatus,businessVertical);
		if(data.isEmpty())
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unable to fetch users For Salary Concilition", data);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY, data);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_SALARY_RECONCILIATION_IC)
	public ResponseEntity<Object> getIndirectCostForSalaryReconciliation(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year,@RequestParam String userStatus,@RequestParam(required = false) String businessVertical){
		List<Map<String, Object>> users=consolidatedService.getUsersForSalaryReconcilliation(accessToken,month+1,year,userStatus,businessVertical);
		Map<String,Object> data=consolidatedService.getIndirectCostForSalaryReconciliation(accessToken,month+1,year,userStatus,businessVertical,users);
		if(data==null)
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unable to fetch IC for users", data);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY, data);
	}
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_SALARY_DIFFERENCE)
	public ResponseEntity<Object> getSalaryDifference(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year){
		Map<String,Object> data=consolidatedService.getSalaryDifference(accessToken,month+1,year);
		if(data==null)
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.UNABLE_TO_FETCH_DATA, data);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY, data);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_USER_SNAPSHOT)
	public ResponseEntity<Object> getUserSnapShot(@RequestHeader("Authorization") String accessToken, @RequestParam Long projectId){
		List<Object> data=consolidatedService.getUserSnapShot(accessToken,projectId);
		if(data.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.UNABLE_TO_FETCH_DATA, data);
			}
		else {
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY, data);
		}
	}
	
	@GetMapping(UrlMappings.GET_PROJECT_BILLING)
	public ResponseEntity<Object> getBillingComplianceProjectData(@RequestHeader("Authorization") String accessToken,
			@RequestParam long projectId, @RequestParam Integer month, @RequestParam Integer year) {
		boolean isAuthorised = utilityService.hasProjectAccess(projectId, accessToken);
		if (isAuthorised) {
			Map<String, Object> data = consolidatedService.getBillingComplianceProjectData(accessToken, projectId, month+1, year);
			if (data == null)
				return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false,
						ConstantUtility.UNABLE_TO_FETCH_DATA, data);
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY,
					data);
		}
		return ResponseHandler.errorResponse("Access Denied", HttpStatus.FORBIDDEN, null);
	}
	
}
