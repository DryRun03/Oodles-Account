package com.oodles.repository.invoice;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.invoice.InvoiceStatus;

public interface InvoiceStatusRepository  extends JpaRepository<InvoiceStatus,Serializable>  {

	List<InvoiceStatus> findAll();
	
	InvoiceStatus findById(Long id);
	
	InvoiceStatus findByStatusName(String name);
}
