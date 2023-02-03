package com.oodles.controller.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.interfaces.util.IProjectInvoiceUtilityService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class ProjectInvoiceUtilityController {
	
	@Autowired
	IProjectInvoiceUtilityService projectInvoiceUtilService;
	
	@GetMapping(UrlMappings.GET_ACTUAL_INVOICE)
	public ResponseEntity<Object> getActualInvoice(@RequestHeader("Authorization") String accessToken,@RequestParam int month,
			@RequestParam int year, @RequestParam String businessVertical) {
		List<Object> data = projectInvoiceUtilService.getActualInvoice(accessToken, year, month+1, businessVertical);
		if (!data.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, data);
		}
		return ResponseHandler.errorResponse(ConstantUtility.FAILED, HttpStatus.CONFLICT);
	}

}
