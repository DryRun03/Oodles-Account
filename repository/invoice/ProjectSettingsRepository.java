package com.oodles.repository.invoice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.invoice.InvoiceProjectSettings;

public interface ProjectSettingsRepository extends JpaRepository<InvoiceProjectSettings, Long>{

	InvoiceProjectSettings findByProjectId(Long projectId);

}
