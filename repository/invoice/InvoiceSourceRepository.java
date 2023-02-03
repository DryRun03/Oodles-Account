package com.oodles.repository.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.invoice.InvoiceSource;

public interface InvoiceSourceRepository extends JpaRepository<InvoiceSource, Long>{

	InvoiceSource findByIdAndIsArchived(Long id, boolean b);

	List<InvoiceSource> findAllByIsArchived(boolean b);

}
