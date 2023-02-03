package com.oodles.accountspayable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.accountspayable.domain.PayableStatus;

@Repository
public interface PayableStatusRepository extends JpaRepository<PayableStatus, Long> {
	PayableStatus findById(long id);
}
