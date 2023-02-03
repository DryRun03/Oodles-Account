package com.oodles.dto.invoice;

public class ProjectSettingsDto {

	private Long paymentModeId;

	private Long paymentTermsId;

	private Long billingCycleId;

	private String clientAddress;

	private String gstNumber;

	private Long projectId;

	private String companyName;

	private String emailId;

	public Long getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(Long paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public Long getPaymentTermsId() {
		return paymentTermsId;
	}

	public void setPaymentTermsId(Long paymentTermsId) {
		this.paymentTermsId = paymentTermsId;
	}

	public Long getBillingCycleId() {
		return billingCycleId;
	}

	public void setBillingCycleId(Long billingCycleId) {
		this.billingCycleId = billingCycleId;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
