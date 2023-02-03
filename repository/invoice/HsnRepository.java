package com.oodles.repository.invoice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.invoice.InvoiceHSN;

public interface HsnRepository extends JpaRepository<InvoiceHSN, Long>{

	InvoiceHSN findByIsArchived(boolean b);

}
