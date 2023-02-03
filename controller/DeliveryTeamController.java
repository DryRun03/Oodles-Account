package com.oodles.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.oodles.util.ResponseHandler;
import org.springframework.http.HttpStatus;

import com.oodles.service.DeliveryTeamService;
import com.oodles.service.ProjectInvoiceService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.MessageUtility;
import com.oodles.util.UrlMappings;

@RestController
public class DeliveryTeamController {
	
	@Autowired
	DeliveryTeamService deliveryTeamService;
	
	@Autowired
	ProjectInvoiceService projectInvoiceService;
	
	@Autowired
	FeignLegacyInterface legacyInterface;
	
	@GetMapping(UrlMappings.GET_PROJECT_MARGIN)
	public ResponseEntity<Object> getTeamHeadWiseProjectMargin(@RequestHeader("Authorization") String accessToken,@RequestParam Long teamHeadId,@RequestParam String bu,@RequestParam int year,@RequestParam int month ) {
		List<Object> response = deliveryTeamService.getTeamHeadWiseProjectMargin(accessToken,teamHeadId,bu,year,month+1);
		if (!response.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS,
					response);

		} else {
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false,
					"No Data Available", response);

		}
	}
	
	
	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_DATA_LINE_CHART)
	public ResponseEntity<Object> getTeamHeadWiseDataLineChart(@RequestHeader("Authorization") String accessToken,@RequestParam Long teamHeadId,
			@RequestParam String bu,@RequestParam String year) {
		List<HashMap<String, Object>> data = deliveryTeamService.getTeamHeadWiseDataLineChart( accessToken, teamHeadId, bu, year);
		if (!data.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, data);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false,
				MessageUtility.getMessage(ConstantUtility.FAILED), data);
	}
	
	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_AVERAGE_DISPUTED_INVOICE_PERCENTAGE)
	public ResponseEntity<Object> getTeamHeadWiseAverageDisputedInvoicePercentage(@RequestHeader("Authorization") String accessToken,@RequestParam Long teamHeadId,@RequestParam String bu,@RequestParam String year)throws Exception {
		 Map<String, Object> response = deliveryTeamService.getTeamHeadWiseAverageDisputedInvoicePercentage(accessToken,teamHeadId,bu,year);
		if (response != null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);

		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false,
				MessageUtility.getMessage(ConstantUtility.FAILED), response);
	}
	
	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_INVOICE_DATA)
	public ResponseEntity<Object> getTeamHeadWiseInvoiceData(@RequestHeader String authorization, @RequestParam String month,@RequestParam String year,@RequestParam String businessVertical,@RequestParam Long teamHeadId,@RequestParam String currencyType) {
//			List<Map<String,Object>> allProjectData = deliveryTeamService.getTeamHeadWiseProjects(authorization,teamHeadId,businessVertical);
		List<Map<String,Object>> allProjectData = projectInvoiceService.getProjectDetails(authorization);
		Map<String, Object> response = deliveryTeamService.getInvoiceData(authorization,  year,month,allProjectData,teamHeadId,businessVertical,currencyType);
			if (!response.isEmpty())
				return ResponseHandler.generateResponse(HttpStatus.CREATED, true,
						MessageUtility.getMessage("data.fetched.successfully"), response);
			else
				return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false,
						MessageUtility.getMessage(ConstantUtility.FAILED), response);
	}
	
	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_IFSD_DATA)
	public ResponseEntity<Object> getTeamHeadWiseIfsdData(@RequestHeader String authorization, @RequestParam String businessVertical,
			@RequestParam Long teamHeadId) {
		List<Object> response = deliveryTeamService.getTeamHeadWiseIfsdData(authorization, teamHeadId,
				businessVertical);
		if (!response.isEmpty())
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true,
					MessageUtility.getMessage("data.fetched.successfully"), response);
		else
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false,
					MessageUtility.getMessage(ConstantUtility.FAILED), response);
	}
	

	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_INVOICE_BILLING)
	public ResponseEntity<Object> getInvoiceTrends(@RequestHeader String authorization, @RequestParam int month,
			@RequestParam String year,@RequestParam String businessVertical,@RequestParam Long teamHeadId) throws NumberFormatException, Exception 
		 {
		Map<String,Object> response = deliveryTeamService.getInvoiceTrends(authorization, teamHeadId,
				businessVertical,month+1,year);
		if (response!=null)
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true,
					MessageUtility.getMessage("data.fetched.successfully"), response);
		else
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false,
					MessageUtility.getMessage(ConstantUtility.FAILED), response);
	}
	
	@GetMapping(UrlMappings.GET_OVERDUE_INVOICE)
	public ResponseEntity<Object> projectWiseYearlyDisputedInvoices(@RequestHeader("Authorization") String accessToken,
			@RequestParam int year, @RequestParam String buFilter, @RequestParam String projectStatus, 
			@RequestParam(defaultValue = "5", required = false) Long invoiceStatus,@RequestParam() Long teamHeadId) {
		Map<String, Object> responseObj = projectInvoiceService.diputedInvoiceYearly(year, accessToken, buFilter, projectStatus, invoiceStatus, teamHeadId);
		if(!responseObj.isEmpty()) { 
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, responseObj);
		}
		else {
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, true, ConstantUtility.NO_CONTENT, responseObj);
		}
	}
	
	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_INVOICE_PIPELINE)
	public ResponseEntity<Object> getInvoicePipeline(@RequestHeader("Authorization") String accessToken,@RequestParam int month,@RequestParam int year, @RequestParam String projectType, 
			@RequestParam String businessVertical,@RequestParam() Long teamHeadId) {
		List<Object> projects=deliveryTeamService.getInvoicePipeline(accessToken,month+1,year,projectType,businessVertical,teamHeadId);
		if(!projects.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Projects Fetched Successfully", projects);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unbale to fetch Projects", projects);
	}
	
	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_TOTAL_MARGIN)
	public ResponseEntity<Object> getTotalMargin(@RequestHeader String authorization,@RequestParam int month,@RequestParam int year,@RequestParam String businessVertical,@RequestParam Long teamHeadId) {
		Map<String, Object> response = deliveryTeamService.getTeamHeadWiseAverageDisputedInvoicePercentage(authorization,teamHeadId,businessVertical,Integer.toString(year));
		Map<String,Object> totalMargin=deliveryTeamService.getTotalMargin(month+1,Integer.toString(year),businessVertical,teamHeadId);
		if(totalMargin!=null) {
			if(totalMargin.containsKey("totalMargin") ) {
				Double revenue=new Double(totalMargin.get("overallInvoicesInRupee").toString());
				Double disputedAmount = (new Double(response.get("averageDisputedPercentage").toString()) * revenue)/100;
				Double netMargin = new Double(totalMargin.get("totalMargin").toString()) - disputedAmount;
				totalMargin.put("disputedAmountYtd", disputedAmount);
				totalMargin.put("netMargin", 0D);
				totalMargin.put("netMarginPerc", 0D);

				if (new Double(totalMargin.get("totalMarginPerc").toString()) != 0D) {
					totalMargin.put("netMargin", netMargin);
					totalMargin.put("netMarginPerc", new Double(totalMargin.get("totalMarginPerc").toString())- new Double(response.get("averageDisputedPercentage").toString()));
				}
			    
			}
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Total Margin Fetched Successfully", totalMargin);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unbale to fetch Total Margin", null);
	}
	@GetMapping(UrlMappings.GET_CUMMULATIVE_DATA)
	public ResponseEntity<Object> getCummulativeData(@RequestHeader String authorization,@RequestParam String businessVertical, @RequestParam(required = false) Integer month,@RequestParam(required = false) Integer year) {
		List<Map<String, Object>> deliveryTeamsData=(List<Map<String, Object>>) legacyInterface.getDeliveryTeam(authorization, businessVertical,month,year).get("data");
		if(month==null && year==null) {
			month=LocalDateTime.now().minusMonths(1).getMonthValue();
			year= LocalDateTime.now().minusMonths(1).getYear();
		}
		Map<String,Object> totalMargin=deliveryTeamService.getCumulativeCost(month+1,Integer.toString(year),businessVertical, deliveryTeamsData);
		if(!totalMargin.isEmpty()) {
			Map<String, Object> response = projectInvoiceService.getAverageDisputedPercentage(new Long(year),businessVertical,authorization);
			if(totalMargin.containsKey("margin")) {
				Double revenue=new Double(totalMargin.get("overallInvoicesInRupee").toString());
				Double disputedAmount = (new Double(response.get("averageDisputedPercentage").toString()) * revenue)/100;
//				Double netMargin = new Double(totalMargin.get("totalMargin").toString())-disputedAmount;
				totalMargin.put("disputedPerc", response.get("averageDisputedPercentage").toString());
				totalMargin.put("disputedAmountInRupees",disputedAmount);
//				totalMargin.put("netMargin", netMargin);
//				totalMargin.put("netMarginPerc", new Double(totalMargin.get("totalMarginPerc").toString())- new Double(response.get("averageDisputedPercentage").toString()));
			}
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Total Margin Fetched Successfully", totalMargin);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Unbale to fetch Total Margin", null);
	}
	@GetMapping(UrlMappings.GET_TEAM_HEAD_WISE_YTD)
	public ResponseEntity<Object> getDeliveryHeadWiseYTD(@RequestHeader("Authorization") String accessToken,@RequestParam() String bu,@RequestParam String year) {
		List<Map<String,Object>> deliveryTeamData = (List<Map<String,Object>>) legacyInterface
				.getDeliveryHeadWiseYearlyProjects(accessToken,bu,Integer.parseInt(year.toString())).get("data");
		List<Map<String, Object>> projects=deliveryTeamService.getDeliveryHeadWiseYTD(accessToken,year,deliveryTeamData);
		if(!projects.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Data Fetched Successfully", projects);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, "Data Not Found", projects);
	}
}