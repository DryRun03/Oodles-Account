package com.oodles.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.Interfaces.ICompanyMarginService;
import com.oodles.domain.ReserveSnapShot;
import com.oodles.util.ConstantUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

/**
 * Rest Controller For Company P/L Page
 * @author shivangi
 *
 */
@RestController
public class CompanyMarginController {
	
	@Autowired
	ICompanyMarginService companyMarginService;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CompanyMarginController.class);
	
	/**
	 * Fetch Invoice Data Bu Wise
	 * 
	 * @param authorization
	 * @param month
	 * @param year
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_INVOICE_TOTAL_COMPANY)
	public ResponseEntity<Object> getBuWiseInvoiceTotal(@RequestHeader String authorization,@RequestParam int month,@RequestParam int year) {
		Map<String, Object> invoiceTotal=companyMarginService.getBuWiseInvoiceTotal(month+1,Integer.toString(year));
		if(invoiceTotal!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Invoices Fetched Successfully", invoiceTotal);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unbale to fetch Invoice Amount", null);
	}
	
	
	/**
	 * Calculate company's Total Cost
	 * 
	 * @param authorization
	 * @param month
	 * @param year
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_PROJECT_COST_TOTAL_COMPANY)
	public ResponseEntity<Object> getDirectCostBuWise(@RequestHeader String authorization,@RequestParam int month,@RequestParam int year) {
		Map<String, Object> invoiceTotal=companyMarginService.getBuWiseInvoiceTotal(month+1,Integer.toString(year));
		List<Object> buWiseUsers=companyMarginService.getCompanywiseData(month+1,year);
		Map<String,Object> directCostTotal=companyMarginService.getDirectCostBuWiseV2(month+1,year,buWiseUsers,invoiceTotal);
		if(directCostTotal!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Project Cost Fetched Successfully", directCostTotal);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.UNABLE_TO_FETCH_DATA, null);
	}
	
	/**
	 * Get companywise bu count
	 * 
	 * @param accessToken
	 * @param month
	 * @param year
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN','DASHBOARD_ADMIN')")
	@GetMapping(UrlMappings.GET_COMPANYWISE_DATA)
	public ResponseEntity<Object> getCompanywiseData(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year){
		List<Object> companyProjects=companyMarginService.getCompanywiseData(month+1,year);
		if(companyProjects!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "CompanyWise Data Fetched", companyProjects);
		}
		return ResponseHandler.errorResponse(ConstantUtility.UNABLE_TO_FETCH_DATA, HttpStatus.EXPECTATION_FAILED, null);
	}
	
	/**
	 * Calculates overall Margin for company
	 * 
	 * @param accessToken
	 * @param month
	 * @param year
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN','DASHBOARD_ADMIN')")
	@GetMapping(UrlMappings.GET_COMPANY_MARGIN)
	public ResponseEntity<Object> getCompanyMargin(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year){
		Map<String, Object> invoiceTotal=companyMarginService.getBuWiseInvoiceTotal(month+1,Integer.toString(year));
		List<Object> buWiseUsers=companyMarginService.getCompanywiseData(month+1,year);
		Map<String,Object> directCostTotal=companyMarginService.getDirectCostBuWise(month+1,year,buWiseUsers,invoiceTotal);
		Map<String, Object> companyMargins=companyMarginService.getCompanyMargin(accessToken,directCostTotal,invoiceTotal,month+1,year);
		if(companyMargins!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "CompanyWise Margins Fetched", companyMargins);
		}
		return ResponseHandler.errorResponse(ConstantUtility.UNABLE_TO_FETCH_DATA, HttpStatus.EXPECTATION_FAILED, null);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_COMPANY_PL)
	public ResponseEntity<Object> getCompanyPL(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year){
		Map<String, Object> companyMargins=companyMarginService.getCompanyPL(accessToken,month+1,year);
		if(companyMargins!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "CompanyWise P/L Fetched", companyMargins);
		}
		return ResponseHandler.errorResponse(ConstantUtility.UNABLE_TO_FETCH_DATA, HttpStatus.EXPECTATION_FAILED, null);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_TOTAL_COST_DIVISION)
	public ResponseEntity<Object> getTotalCostDivision(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year,@RequestParam String businessVertical){
		Map<String, Object> invoiceTotal=companyMarginService.getBuWiseInvoiceTotal(month+1,Integer.toString(year));
		Map<String, Object> totalCostData=companyMarginService.getTotalCostDivision(accessToken,month+1,year,businessVertical,invoiceTotal);
		if(totalCostData!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Total Cost Division fetched successfully", totalCostData);
		}
		return ResponseHandler.errorResponse(ConstantUtility.UNABLE_TO_FETCH_DATA, HttpStatus.EXPECTATION_FAILED, null);
	}
	
	
	/**
	 * @author shivangi
	 * @param accessToken
	 * @param month
	 * @param year
	 * @param projectId
	 * @return the list of employees of non billable Project alongwith with their indirect Cost
	 */
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_INDIRECT_COST_DIVISION)
	public ResponseEntity<Object> getIndirectCostDivision(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year,@RequestParam Long projectId){
		Map<String, Object> indirectCost=companyMarginService.getIndirectCostDivision(accessToken,month+1,year,projectId);
		if(indirectCost!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Indirect Cost Division fetched successfully", indirectCost);
		}
		return ResponseHandler.errorResponse(ConstantUtility.UNABLE_TO_FETCH_DATA, HttpStatus.EXPECTATION_FAILED, null);
	}
	
	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.GET_REIMBURSEMENT_COST)
	public ResponseEntity<Object> getReimbursementCost(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year){
		Double reimbursementCost=companyMarginService.getReimbursementCost(accessToken,month+1,year);
		if(reimbursementCost!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "ReimbursementCost fetched successfully", reimbursementCost);
		}
		return ResponseHandler.errorResponse(ConstantUtility.UNABLE_TO_FETCH_DATA, HttpStatus.EXPECTATION_FAILED, null);
	}

	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.FLUSH_DIRECT_COST_CACHE)
	public ResponseEntity<Object> flushDirectCostCache(@RequestHeader("Authorization") String accessToken){
		companyMarginService.flushDirectCostCache();
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, null);

	}

	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@GetMapping(UrlMappings.FLUSH_TEAM_DATA)
	public ResponseEntity<Object> flushTeamData(@RequestHeader("Authorization") String accessToken){
		companyMarginService.flushTeamData();
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, null);

	}
	

}
