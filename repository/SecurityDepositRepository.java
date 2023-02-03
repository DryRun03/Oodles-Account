package com.oodles.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oodles.domain.SecurityDeposit;

@Repository
public interface SecurityDepositRepository extends JpaRepository <SecurityDeposit,  Serializable>{
	
	SecurityDeposit findById(long id);
	@Query(value="select * from security_deposit where  is_deleted=:b",nativeQuery=true)
	List<SecurityDeposit> findAllByIsDeleted(boolean b);
	List<SecurityDeposit> findAllByProjectIdAndIsDeleted(long projectId,boolean b);
	List<SecurityDeposit> findAllByIsDeletedAndProjectId(boolean b, Long projectId);
	List<SecurityDeposit> findAllByIsDeletedAndProjectIdIn(boolean b, List<Long> projectIds);
}
