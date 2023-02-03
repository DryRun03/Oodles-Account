package com.oodles.repository.invoice;
import java.io.Serializable;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oodles.domain.invoice.InvoiceBank;
import com.oodles.domain.invoice.ProjectInvoice;

@Repository
public interface ProjectInvoiceRepository extends JpaRepository <ProjectInvoice,  Serializable>{
	
	ProjectInvoice findById(Long id); 
	
	List<ProjectInvoice>  findByIsDeletedAndInvoiceCycleIdAndIsInternal(boolean b,Long id,boolean isInternal);
	List<ProjectInvoice> findByIsDeletedAndPaymentTermsIdAndIsInternal(boolean b,Long id,boolean isInternal);
	List<ProjectInvoice>  findByIsDeletedAndModeOfPaymentIdAndIsInternal(boolean b,Long id,boolean isInternal);

	
	List<ProjectInvoice> findByProjectAndMonthAndYearAndIsDeletedAndIsInternal(String name,String month,String year,boolean b,boolean isInternal);
	
	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndInvoiceStatusAndIsInternal(String month,String year,boolean b,Long id,boolean isInternal);
	List<ProjectInvoice> findByProjectAndYearAndIsDeletedAndIsInternal(String name,String year,boolean b,boolean isInternal);
	
	List<ProjectInvoice>  findByMonthAndInvoiceStatusAndYearAndIsDeletedAndIsInternal(String month,Long id,String year,boolean b,boolean isInternal);
	List<ProjectInvoice> findByMonthAndYearAndIsDeletedAndIsInternal(String month,String year,boolean b,boolean isInternal);
	List<ProjectInvoice> findByBillingDateBetween(Date d1,Date d2);
	List<ProjectInvoice> findByDueDateBetween(Date d1,Date d2);
	List<ProjectInvoice> findByReceivedOnBetween(Date d1,Date d2);
	
	List<ProjectInvoice> findByProjectIdAndMonthAndYearAndIsDeletedAndIsInternal(long id,String month,String year,boolean b,boolean isInternal);
	List<ProjectInvoice>  findByProjectIdAndIsDeletedAndIsInternal(long id,boolean b,boolean isInternal);
	List<ProjectInvoice> findAllByIsDeletedAndIsInternal(boolean b,boolean isInternal);

	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndProjectIdAndIsInternal(String monthName, String year, boolean b,
			long parseLong,boolean isInternal);
	
	List<ProjectInvoice> findAllByInvoiceStatusAndMonthAndYearAndIsDeletedAndIsInternal(Long status,String month,String year,boolean delete ,boolean isInternal);
	Long countByIsDeleted(boolean b);
	Long countByInvoiceStatusAndIsDeletedAndIsInternal(Long status,boolean b,boolean isInternal);
	List<ProjectInvoice> findByProjectIdAndIsDeletedAndIsInternalOrderByIdAsc(Long projectId, boolean b,boolean isInternal);
	List<ProjectInvoice> findAllByMonthAndYearAndProjectIdAndIsDeletedAndIsInternal(String monthName, String year,Long projectId, boolean b,boolean isInternal);
	List<ProjectInvoice> findAllByProjectIdAndIsDeletedAndIsInternal(Long projectId,boolean isDeleted,boolean isInternal);
	List<ProjectInvoice> findAllByProjectIdAndMonthAndIsDeletedAndIsInternal(Long projectId,String month,boolean isDeleted,boolean isInternal);
	List<ProjectInvoice> findByProjectIdAndYearAndIsDeletedAndIsInternal(long projectId, String year, boolean b,boolean isInternal);

	List<ProjectInvoice> findAllByProjectIdAndIsDeletedAndIsInternal(long projectId, boolean isDeleted,boolean isInternal);
	Optional<List<ProjectInvoice>> findAllByYearAndInvoiceStatusAndIsDeletedAndIsInternal(String year, long invoiceStatus , 
			boolean isDeleted,boolean isInternal);
	List<ProjectInvoice> findAllByProjectIdAndInvoiceStatusAndIsDeletedAndIsInternal(long projectId, long invoiceStatus,
			boolean isDeleted,boolean isInternal);

	Optional<List<ProjectInvoice>> findAllByProjectIdAndInvoiceStatusAndIsDeletedAndYearAndIsInternal(long projectId, Long i, boolean b, String year,boolean isInternal);
	
	List<ProjectInvoice> findAllByProjectIdAndInvoiceStatusAndYearAndIsDeletedAndIsInternal(long projectId, Long i, String year, boolean b,boolean isInternal);

	List<ProjectInvoice> findAllByYearAndIsDeletedAndIsInternal(String year, boolean b, boolean isInternal);
	
// 		List<ProjectInvoice> findAllByYearAndIsDeletedAndIsInternalAndInvoiceStatusNot(String year, boolean b, boolean isInternal, long l);

	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndIsInternal(String month, String year, boolean b,
			boolean c);

	List<ProjectInvoice> findAllByRaisedToBuAndMonthAndYearAndIsDeletedAndIsInternal(Long buId, String month, String year,
			boolean b,boolean isInternal);

	List<ProjectInvoice> findAllByRaisedFromBuAndMonthAndYearAndIsDeletedAndIsInternal(Long buId, String month, String year,
			boolean b,boolean isInternal);

	List<ProjectInvoice> findAllByMonthAndYearAndModeOfPaymentIdAndIsDeleted(String month, String year, Long modeOfPaymentId,Boolean deleted);

	List<ProjectInvoice> findAllByMonthAndYear(String month, String year);
	
	List<ProjectInvoice> findAllByInvoiceStatusAndIsDeletedAndIsInternal(Long invoiceStatusId,boolean isDeleted,boolean isInternal);

	List<ProjectInvoice> findAllByBankAndIsDeleted(InvoiceBank bank, boolean b);

	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndIsInternalAndInvoiceStatusNot(String monthName,
			String string, boolean b, boolean c, long l);

	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndProjectIdAndInvoiceStatusNot(String string, String string2,
			boolean b, Long projectId, long l);

	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndProjectId(String monthName, String string, boolean b,
			Long projectId);

	List<ProjectInvoice> findAllByMonthAndYearAndBankAndIsDeleted(String month, String year, InvoiceBank bank,Boolean deleted);

	List<ProjectInvoice> findAllByYearAndBankAndIsDeleted(String year, InvoiceBank bank,Boolean deleted);

	List<ProjectInvoice> findAllByYearAndModeOfPaymentIdAndIsDeleted(String year, Long id,Boolean deleted);

	List<ProjectInvoice> findAllByMonthAndYearAndIsDeleted(String month, String year, boolean b);

	List<ProjectInvoice> findAllByYearAndIsDeleted(String year, boolean b);

	List<ProjectInvoice> findByProjectIdAndMonthAndYearAndIsDeleted(Long projectId, String month, String year,
			boolean b);

	List<ProjectInvoice> findAllByProjectIdAndInvoiceStatusAndIsDeleted(Long projectId, long l, boolean b);

	List<ProjectInvoice> findAllByProjectIdAndYearAndIsDeleted(long projectId, String year, boolean isDeleted);

	Optional<List<ProjectInvoice>> findAllByYearAndInvoiceStatusAndIsDeletedAndIsInternalAndProjectIdIn(String valueOf,
			Long invoiceStatus, boolean b, boolean c, List<Long> projectIds);

	List<ProjectInvoice> findByProjectIdInAndMonthAndYearAndIsDeletedAndIsInternal(List<Long> projectIds, String month,
			String year, boolean isDeleted, boolean isInternal);
	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndIsInternalAndProjectIdIn(String monthName, String year,
			boolean b, boolean c, List<Long> projectIds);
	
	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndProjectIdIn(String monthName, String year,
			boolean b, List<Long> projectIds);

	List<ProjectInvoice> findAllByProjectId(Long projectId);

	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndProjectIdAndIsInternalAndInvoiceStatusNot(String monthName,
			String string, boolean b, Long projectId, boolean c, long l);

	List<ProjectInvoice> findAllByProjectIdInAndYearAndIsDeletedAndIsInternal(List<Long> projectIds, String year,
			boolean b, boolean c);
	
	List<ProjectInvoice> findAllByMonthAndYearAndIsDeletedAndInvoiceStatusNot(String monthName, String string,
			boolean b, long l);

	List<ProjectInvoice> findAllByYearAndIsDeletedAndIsInternalAndProjectIdIn(String year, boolean b, boolean c,
			List<Long> projectIds);

	List<ProjectInvoice> findAllByIsDeleted(boolean isDeleted);

	List<ProjectInvoice> findAllByYearAndIsDeletedAndIsInternalAndInvoiceStatusNot(String year, boolean b, boolean c,
			long l);

	List<ProjectInvoice> findAllByIsDeletedAndIsInternalAndInvoiceStatusNot(boolean b, boolean c, long l);
	
	List<ProjectInvoice> findAllByMonthInAndYearAndIsDeletedAndIsInternal(List<String> month, String year, boolean b,
			boolean c);
	List<ProjectInvoice> findAllByMonthInAndYearAndIsDeleted(List<String> month, String year, boolean b);

	List<ProjectInvoice> findAllByProjectIdInAndYearAndMonthInAndIsDeletedAndIsInternal(List<Long> activeProjects,
			String year, List<String> monthList, boolean b, boolean c);

	List<ProjectInvoice> findAllByBankLocationId(long id);

}
