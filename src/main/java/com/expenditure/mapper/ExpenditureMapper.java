package com.expenditure.mapper;

import org.mapstruct.Mapper;

import com.expenditure.commons.tos.ExpensesTo;
import com.expenditure.models.Expenses;

@Mapper
public interface ExpenditureMapper {

	ExpensesTo expensesToExpensesTo(Expenses expenses);

	Expenses expensesToToExpenses(ExpensesTo expensesTo);

}
