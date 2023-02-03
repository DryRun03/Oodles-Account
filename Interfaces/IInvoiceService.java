package com.oodles.Interfaces;

import java.util.Map;

import com.oodles.domain.invoice.ProjectInvoice;
import com.oodles.domain.invoice.ProjectInvoiceItem;
import com.oodles.dto.ProjectInvoiceDto;
import com.oodles.dto.invoice.InvoiceSlipDto;
import com.oodles.dto.invoice.ProjectInvoiceGenerateDto;
import com.oodles.dto.invoice.ProjectInvoiceItemDto;
import com.oodles.dto.invoice.ProjectInvoiceItemGetDto;

public interface IInvoiceService {

	Map<String, Object> viewInvoice(String authorization, Long projectInvoiceId, Boolean isInternalCall, Boolean isIfsd);

	Object addProjectInvoiceForCreation(ProjectInvoiceDto projectInvoiceDto, String authorization);

	ProjectInvoiceItemGetDto editInvoiceItems(String authorization, ProjectInvoiceItemGetDto invoiceItemDto);

	ProjectInvoiceItem deleteInvoiceItems(String authorization, Long id);

	Map<String, Object> saveGeneratedInvoice(String accessToken, ProjectInvoiceGenerateDto invoiceDto);

	Map<String, Object> generatePDFFromHTML(String authorization, InvoiceSlipDto slipDto) throws Exception;

	Object resetInvoice(Long invoiceId, boolean  isIfsd);

	ProjectInvoiceItem addInvoiceItem(ProjectInvoiceItemDto invoiceItemDto, String accessToken);

}
