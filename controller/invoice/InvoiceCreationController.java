package com.oodles.controller.invoice;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revisions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.Interfaces.ICompanyMarginService;
import com.oodles.Interfaces.IInvoiceService;
import com.oodles.domain.invoice.InvoiceProjectSettings;
import com.oodles.domain.invoice.InvoiceType;
import com.oodles.domain.invoice.ProjectInvoice;
import com.oodles.domain.invoice.ProjectInvoiceItem;
import com.oodles.dto.ProjectInvoiceDto;
import com.oodles.dto.invoice.InvoiceSlipDto;
import com.oodles.dto.invoice.ProjectInvoiceGenerateDto;
import com.oodles.dto.invoice.ProjectInvoiceItemDto;
import com.oodles.dto.invoice.ProjectInvoiceItemGetDto;
import com.oodles.dto.invoice.ProjectSettingsDto;
import com.oodles.service.ProjectInvoiceService;
import com.oodles.service.ProjectMarginService;
import com.oodles.service.invoice.InvoiceRevisionService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.MessageUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class InvoiceCreationController {
	
	@Autowired
	ProjectInvoiceService projectInvoiceService;
	
	@Autowired
	ICompanyMarginService companyMarginService;
	
	@Autowired
	ProjectMarginService projectMarginService;
	
	@Autowired
	IInvoiceService invoiceService;
	
	@Autowired
	InvoiceRevisionService invoiceRevisionService;
	
	@PostMapping(UrlMappings.INVOICE_CREATION)
	public ResponseEntity<Object> addProjectInvoice(@RequestBody ProjectInvoiceDto projectInvoiceDto, @RequestHeader String authorization) {
		if ((!projectInvoiceDto.getIsInternal() && projectInvoiceDto.getProjectId() != 0) || projectInvoiceDto.getIsInternal()) {
			Object response = invoiceService.addProjectInvoiceForCreation(projectInvoiceDto, authorization);
			if (response != null) {
				projectInvoiceService.flushInvoiceChart();
				companyMarginService.flushInvoicesCache();
				projectMarginService.flushTotalBuMargins();
				projectMarginService.flushBuMargins();
				projectInvoiceService.flushYtdPerc();
				 projectInvoiceService.flushgetInternalInvoices();
				return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, response);
			} else
				return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false, MessageUtility.getMessage("Please.fill.all.mandatory.fields"), null);
		} else
			return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false, "Project Id can't be 0", null);
	}
	
	@GetMapping(UrlMappings.INVOICE_HISTORY)
	public ResponseEntity<Object> getProjectInvoiceHistory(@RequestHeader String authorization,@RequestParam Long projectInvoiceId) {
		List<Map<String, Object>> InvoiceHistory = invoiceRevisionService.getInvoiceHistory(authorization,projectInvoiceId);
		if (Objects.nonNull(InvoiceHistory)) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.FETCHED_SUCCESSFULLY, InvoiceHistory);
		} else {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.FAILED,
					ConstantUtility.DETAILS_NOT_FOUND);
		}
	}
	
	@GetMapping(UrlMappings.GET_INVOICE_TYPE)
	public ResponseEntity<Object> getInvoiceType(@RequestHeader String authorization){
		List<InvoiceType> invoiceTypes=projectInvoiceService.getInvoiceType();
		if(!invoiceTypes.isEmpty())
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceTypes);
		else
			return ResponseHandler.errorResponse(ConstantUtility.NOT_FOUND, HttpStatus.EXPECTATION_FAILED, null);
	}
	
	@PostMapping(UrlMappings.PROJECT_SETTINGS)
	public ResponseEntity<Object> addProjectSettings(@RequestHeader String authorization, @RequestBody ProjectSettingsDto projectSettingsDto){
		InvoiceProjectSettings projectSettings=projectInvoiceService.addProjectSettings(projectSettingsDto,authorization);
		if(projectSettings!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.ADDED_SUCCESSFULLY, projectSettings);
		else
			return ResponseHandler.errorResponse(ConstantUtility.CREATION_FAILED, HttpStatus.EXPECTATION_FAILED, projectSettings);
	}
	
	@GetMapping(UrlMappings.PROJECT_SETTINGS)
	public ResponseEntity<Object> getProjectSettings(@RequestHeader String authorization,@RequestParam Long projectId){
		InvoiceProjectSettings projectSettings=projectInvoiceService.getProjectSettings(authorization, projectId);
		if(projectSettings!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, projectSettings);
		else
			return ResponseHandler.errorResponse(ConstantUtility.FAILED, HttpStatus.EXPECTATION_FAILED, projectSettings);
	}
	
	@GetMapping(UrlMappings.VIEW_INVOICE_DETAILS)
	public ResponseEntity<Object> viewInvoiceDetails(@RequestHeader String authorization,@RequestParam Long projectInvoiceId, @RequestParam(required = false,defaultValue = "false") Boolean isIfsd){
		Map<String,Object> invoiceDetails=invoiceService.viewInvoice(authorization, projectInvoiceId, false, isIfsd);
		if(invoiceDetails!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceDetails);
		else
			return ResponseHandler.errorResponse(ConstantUtility.FAILED, HttpStatus.EXPECTATION_FAILED, invoiceDetails);
	}
	
	@PutMapping(UrlMappings.INVOICE_ITEM)
	public ResponseEntity<Object> editInvoiceItems(@RequestHeader("Authorization") String authorization,@RequestBody ProjectInvoiceItemGetDto invoiceItemDto){
		ProjectInvoiceItemGetDto item=invoiceService.editInvoiceItems(authorization,invoiceItemDto);
		if(item!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.FETCHED_SUCCESSFULLY, item);
		else
			return ResponseHandler.errorResponse(ConstantUtility.UNABLE_TO_FETCH_DATA, HttpStatus.EXPECTATION_FAILED);
	} 
	
	@DeleteMapping(UrlMappings.INVOICE_ITEM)
	public ResponseEntity<Object> deleteInvoiceItems(@RequestHeader("Authorization") String authorization,@RequestParam Long id){
		ProjectInvoiceItem item=invoiceService.deleteInvoiceItems(authorization,id);
		if(item!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.FETCHED_SUCCESSFULLY, item);
		else
			return ResponseHandler.errorResponse(ConstantUtility.NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
	} 
	
	@PatchMapping(UrlMappings.SAVE_INVOICE_SLIP)
	public ResponseEntity<Object> saveGeneratedInvoice(@RequestHeader String authorization,@RequestBody ProjectInvoiceGenerateDto invoiceDto){
		Map<String,Object> invoiceDetails=invoiceService.saveGeneratedInvoice(authorization, invoiceDto);
		if(invoiceDetails!=null)
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceDetails);
		else
			return ResponseHandler.errorResponse(ConstantUtility.FAILED, HttpStatus.EXPECTATION_FAILED, invoiceDetails);
	}
	
	@PostMapping(UrlMappings.DOWNLOAD_INVOICE_SLIP)
	public ResponseEntity<Object> downloadInvoiceSlip(@RequestHeader String authorization,
			@RequestBody InvoiceSlipDto slipDto) {
		Map<String, Object> invoiceDetails = null;
		try {
			invoiceDetails = invoiceService.generatePDFFromHTML(authorization, slipDto);
			if (invoiceDetails != null)
				return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceDetails);
			else
				return ResponseHandler.errorResponse(ConstantUtility.FAILED, HttpStatus.EXPECTATION_FAILED,
						invoiceDetails);
		} catch (Exception e) {
			return ResponseHandler.errorResponse(ConstantUtility.FAILURE, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}
	
	@PatchMapping(UrlMappings.RESET_INVOICE)
	public ResponseEntity<Object> resetInvoice(@RequestHeader String authorization,@RequestParam Long invoiceId,@RequestParam Boolean isIfsd) {
		Object invoice=invoiceService.resetInvoice(invoiceId,isIfsd);
		if(invoice!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoice);
		}
		else {
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILED, null);
		}
	}
	
	@PostMapping(UrlMappings.INVOICE_ITEM)
	public ResponseEntity<Object> addInvoiceItem(@RequestHeader String authorization,@RequestBody ProjectInvoiceItemDto invoiceItemDto) {
		ProjectInvoiceItem invoiceItem=invoiceService.addInvoiceItem(invoiceItemDto,authorization);
		if(invoiceItem!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, invoiceItem);
		}
		else {
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILED, null);
		}
	}

}
