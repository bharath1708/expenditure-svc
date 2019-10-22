package com.expenditure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expenditure.commons.tos.ExpensesTo;
import com.expenditure.commons.urlconstants.ExpenditureURLs;
import com.expenditure.exception.ExpenseNotFound;
import com.expenditure.mapper.ExpenditureMapper;
import com.expenditure.service.ExpenditureSevice;

@RestController
public class ExpenditureController {

	@Autowired
	private ExpenditureMapper expenditureMapper;

	@Autowired
	private ExpenditureSevice expenditureSevice;

	@PostMapping(ExpenditureURLs.ADD_EXPENSE)
	private ExpensesTo addExpenses(@RequestBody ExpensesTo expensesTo) {
		return expenditureMapper
				.expensesToExpensesTo(expenditureSevice.create(expenditureMapper.expensesToToExpenses(expensesTo)));

	}

	@GetMapping(ExpenditureURLs.GET_ALL_EXPENSE)
	private List<ExpensesTo> getAllExpenses() {
		return expenditureMapper
				.expensesToExpensesTo(expenditureSevice.getAllExpenses());

	}
	
	@GetMapping(ExpenditureURLs.GET_EXPENSE)
	private ExpensesTo getExpense(@RequestParam(value=ExpenditureURLs.Params.EXPENSE_ID) final String expenseId) throws ExpenseNotFound {
		return expenditureMapper
				.expensesToExpensesTo(expenditureSevice.getExpense(expenseId));

	}
}
