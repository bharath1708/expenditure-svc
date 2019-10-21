package com.expenditure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.expenditure.commons.tos.ExpensesTo;
import com.expenditure.models.Expenses;

@Mapper(componentModel = "spring")
public interface ExpenditureMapper {

	ExpensesTo expensesToExpensesTo(Expenses expenses);

	Expenses expensesToToExpenses(ExpensesTo expensesTo);

	List<ExpensesTo> expensesToExpensesTo(List<Expenses> expenses);
}
