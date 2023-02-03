package com.oodles.accountspayable.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.accountspayable.domain.AccountsPayable;
import com.oodles.accountspayable.domain.PayableStatus;
import com.oodles.accountspayable.dto.AccountsPayableDataTransfer;
import com.oodles.accountspayable.dto.PayableResponseDto;
import com.oodles.accountspayable.enums.PayableTypes;
import com.oodles.accountspayable.enums.TaxType;
import com.oodles.accountspayable.services.AccountsPayableService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class AccountsPayableController {
	
	@Autowired 
	private AccountsPayableService accountsPayableService;
	
	@GetMapping(UrlMappings.GET_ALL_ACCOUNTS_PAYABLES)
	public ResponseEntity<Object> getAllAccountPayables(@RequestHeader("Authorization") String accessToken,
			@RequestParam int month, @RequestParam int year, @RequestParam Long hsnCodeId){
		List<PayableResponseDto> data = accountsPayableService.getAllAccountsPayableData(month + 1, year, hsnCodeId);
		if(!data.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, data);
		}
		return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, ConstantUtility.FAILED, data);
	}
	
	@PostMapping(UrlMappings.SAVE_ACCOUNT_PAYABLE)
	public ResponseEntity<Object> saveAccountPayableData(@RequestBody AccountsPayableDataTransfer bodyData, 
			@RequestHeader("Authorization") String accessToken){
		PayableResponseDto accountsPayable = accountsPayableService.saveAccountsPayableData(bodyData);
		if(accountsPayable != null) {
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true, ConstantUtility.SUCCESS, accountsPayable);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILED, accountsPayable);
	}
	
	@PutMapping(UrlMappings.UPDATE_ACCOUNT_PAYABLE)
	public ResponseEntity<Object> updateAccountsPayable(@RequestParam("id") long id, @RequestBody AccountsPayableDataTransfer bodyData, 
			@RequestHeader("Authorization") String accessToken){
		PayableResponseDto updatedData = accountsPayableService.updateAccountsPayableData(id, bodyData);
		if(updatedData != null) {
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true, ConstantUtility.SUCCESS, updatedData);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILED, updatedData);
	}
	
	@DeleteMapping(UrlMappings.DELETE_ACCOUNT_PAYABLE)
	public ResponseEntity<Object> deleteAccountsPayable(@RequestHeader("Authorization") String accessToken, @RequestParam("id") long id){
		AccountsPayable accountsPayable = accountsPayableService.deleteAccountsPayableData(id);
		if(accountsPayable != null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, "");
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILED, "");
	}

	@GetMapping(UrlMappings.GET_ACCOUNT_PAYABLE_TYPES)
	public ResponseEntity<Object> getAllPayableTypes(@RequestHeader("Authorization") String accessToken){
		List<PayableTypes> allPayables = accountsPayableService.getAllPayableTypes();
		if(allPayables != null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, allPayables);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILED, allPayables);
	}
	
	@GetMapping(UrlMappings.GET_ALL_TAX_TYPES)
	public ResponseEntity<Object> getAllTaxTypes(@RequestHeader("Authorization") String token){
		List<TaxType> taxTypes = accountsPayableService.getAllPayableTaxTypes();
		if(taxTypes != null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, taxTypes);
		}
		return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILED, "");
	}
	
	@GetMapping(UrlMappings.CALCULATE_TAXES)
	public ResponseEntity<Object> calculateVariousTaxes(@RequestHeader("Authorization") String token, @RequestParam 
			double invoiceAmount, @RequestParam TaxType taxType, @RequestParam double taxPercentage){
		Map<String, Object> calculatedAmount = accountsPayableService.calculateTaxesOfGivenType(taxType, invoiceAmount, 
				taxPercentage);
		return ResponseHandler.generateResponse(HttpStatus.OK, true,ConstantUtility.SUCCESS, calculatedAmount);
	}
	
	@GetMapping(UrlMappings.GET_ALL_PAYABLE_STATUS)
	public ResponseEntity<Object> getAllPayableStatus(@RequestHeader("Authorization") String token){
		List<PayableStatus> payableStatus = accountsPayableService.getAllPayableStatus();
		if(!payableStatus.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, payableStatus);
		}
		return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, ConstantUtility.FAILED, payableStatus);
	}
}

