package com.oodles.Interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oodles.domain.BuExpenses;
import com.oodles.dto.BuExpensesDto;

@Service
public interface IBuExpensesService {

	public List<Object> getExpensesType();

	public BuExpenses addExpenseType(String expenseType);

	public boolean deleteExpenseType(Long id);

	public BuExpenses editExpenseType(BuExpensesDto dto);

}
