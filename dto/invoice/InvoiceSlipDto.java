package com.oodles.dto.invoice;

public class InvoiceSlipDto {

	private String html;
	
	private Long invoiceId;
	
	private Boolean isIfsd;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Boolean getIsIfsd() {
		return isIfsd;
	}

	public void setIsIfsd(Boolean isIfsd) {
		this.isIfsd = isIfsd;
	}
	
}
