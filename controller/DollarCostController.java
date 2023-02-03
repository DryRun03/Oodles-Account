package com.oodles.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.Interfaces.DollarCostService;
import com.oodles.dto.DollarCostDTO;
import com.oodles.service.LoginUtiltiyService;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class DollarCostController {
	
	@Autowired
	private DollarCostService dollarCostService;
	
	@Autowired
	private LoginUtiltiyService loginUtilityService;
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_ALL_DOLLAR_COST)
	public ResponseEntity<Object> getAllCost(@RequestHeader("Authorization") String accessToken,@RequestParam(required=false) Integer year){
		Map<String, Object> allData = dollarCostService.getAllCost(year);
		if(!allData.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fetched Successfully!", allData);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, "Error occured!", allData);
		}
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@PostMapping(UrlMappings.ADD_DOLLAR_COST)
	public ResponseEntity<Object> addCost(@RequestBody DollarCostDTO body, @RequestHeader("Authorization") String accessToken){
		Map<String, Object> response = dollarCostService.saveDollarCost(body, accessToken);
		if(!response.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "Saved Successfully!", response);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false, "This month's cost is already present", response);
		}
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@PostMapping(UrlMappings.UPDATE_DOLLAR_COST)
	public ResponseEntity<Object> updateDollarCost(@RequestBody DollarCostDTO body, @RequestHeader("Authorization") String accessToken, Long id){
		Map<String, Object> response = dollarCostService.updateDollarCost(body, id, accessToken);
		if(response != null) {
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "Saved Successfully!", response);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, "Error occured!", response);
		}
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@DeleteMapping(UrlMappings.DELETE_DOLLAR_COST)
	public ResponseEntity<Object> deleteDollarCost(@RequestHeader("Authorization") String accessToken, Long id){
		dollarCostService.deleteDollarCost(id, accessToken);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, "deleted successfully", null);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_DOLLAR_COST_BY_MONTH_YEAR)
	public ResponseEntity<Object> getCostByMonthAndYear(@RequestHeader("Authorization") String accessToken, int month, int year){
		Map<String, Object> response = dollarCostService.getCostByMonthAndYear( month + 1, year);
		if(response != null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fetched Successfully!", response);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, "No Entry found for given month or last to this month in this year.", response);
		}
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.DOLLAR_COST_LAST_6_MONTH)
	public ResponseEntity<Object> getLast6MonthDollarCostAverage(@RequestHeader("Authorization") String accessToken){
		ArrayList<Object> data = dollarCostService.getLast6MonthAverage();
		if(!data.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Data fetched successfully", data);
		}
		return ResponseHandler.errorResponse("Error occured", HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.DOLLAR_COST_MONTH)
	public ResponseEntity<Object> getDollarCostOfMonth(@RequestHeader("Authorization") String accessToken,@RequestParam String month,@RequestParam int year){
		int monthNum=loginUtilityService.getMonthNumber(month);
		Object dollarCost = dollarCostService.getDollarCostForConversion(monthNum,year);
		if(dollarCost!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Dollar cost fetched successfully", dollarCost);
		}
		return ResponseHandler.errorResponse("Error occured", HttpStatus.EXPECTATION_FAILED);
	}
}
