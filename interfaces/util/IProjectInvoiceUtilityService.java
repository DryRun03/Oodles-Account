package com.oodles.interfaces.util;

import java.util.List;

public interface IProjectInvoiceUtilityService{

	List<Object> getActualInvoice(String accessToken, int year, int i, String businessVertical);

}
