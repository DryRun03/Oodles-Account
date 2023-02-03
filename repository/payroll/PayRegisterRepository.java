package com.oodles.repository.payroll;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.PayRegister;
import com.oodles.enums.PayRegisterStatus;

/**
 * @author shivangi
 * 
 *         The Interface Payregister Repository
 */
public interface PayRegisterRepository extends JpaRepository<PayRegister, Long> {

	PayRegister findAllById(long id);

	PayRegister findAllByUserId(long userId);

	List<PayRegister> findAllByIsCurrent(boolean b);

	List<PayRegister> findByUserId(long userId);

	PayRegister findAllByUserIdAndIsCurrent(long userId, boolean b);

	List<PayRegister> findByIsCurrentAndStatus(boolean b, PayRegisterStatus complete);

	PayRegister findByIsCurrentAndStatusAndUserId(boolean b, PayRegisterStatus complete, Long userId);
	
	PayRegister findByUserIdAndIsCurrent(long userId, boolean isCurrent);

}
