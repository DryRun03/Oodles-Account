package com.oodles.accountspayable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.oodles.accountspayable.domain.AccountsHead;

@Repository
public interface AccountsHeadRepository extends JpaRepository<AccountsHead, Long> {
	
	List<AccountsHead> findByIsArchive(boolean isArchive);
	
	Optional<AccountsHead> findByTypeAndIsArchive(String type, boolean isArchive);
}
