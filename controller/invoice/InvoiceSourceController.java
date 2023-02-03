package com.oodles.controller.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.invoice.InvoiceSource;
import com.oodles.dto.invoice.InvoiceSourceAddDto;
import com.oodles.dto.invoice.InvoiceSourceUpdateDto;
import com.oodles.service.invoice.InvoiceSourceService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class InvoiceSourceController {
	
	@Autowired
	InvoiceSourceService invoiceSourceService;
	
	@GetMapping(UrlMappings.INVOICE_SOURCES)
	public ResponseEntity<Object> getAllInvoiceSources(@RequestHeader("Authorization") String accessToken) {
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceSourceService.getInvoiceSources(accessToken));
	}
	
	@PostMapping(UrlMappings.INVOICE_SOURCES)
	public ResponseEntity<Object> addInvoiceSource(@RequestHeader("Authorization") String accessToken, @RequestBody InvoiceSourceAddDto invoiceSourceDto) {
		InvoiceSource invoiceSourceDetails=invoiceSourceService.addInvoiceSource(accessToken, invoiceSourceDto);
		if(invoiceSourceDetails!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceSourceDetails);
		else
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.ALREADY_EXIST, invoiceSourceDetails);
	}
	
	@PatchMapping(UrlMappings.INVOICE_SOURCES)
	public ResponseEntity<Object> updateInvoiceSource(@RequestHeader("Authorization") String accessToken, @RequestBody InvoiceSourceUpdateDto invoiceSourceDto) {
		InvoiceSource invoiceSourceDetails=invoiceSourceService.updateInvoiceSource(accessToken, invoiceSourceDto);
		if(invoiceSourceDetails!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceSourceDetails);
		else
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.NOT_FOUND, invoiceSourceDetails);
	}
	
	@DeleteMapping(UrlMappings.INVOICE_SOURCES)
	public ResponseEntity<Object> deleteInvoiceSource(@RequestHeader("Authorization") String accessToken, @RequestParam Long id) {
		InvoiceSource invoiceSourceDetails=invoiceSourceService.deleteInvoiceSource(accessToken, id);
		if(invoiceSourceDetails!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceSourceDetails);
		else
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.NOT_FOUND, invoiceSourceDetails);
	}

}
