package com.expenditure.service;

import java.util.List;
import java.util.Optional;

import com.expenditure.exception.ExpenseNotFound;
import com.expenditure.models.Expenses;

public interface ExpenditureSevice {
	
	Expenses create(Expenses expenses);
	List<Expenses> getAllExpenses();
	Expenses getExpense(String expenseId) throws ExpenseNotFound;

}
