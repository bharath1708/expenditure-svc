package com.expenditure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expenditure.commons.tos.ExpensesTo;
import com.expenditure.commons.urlconstants.ExpenditureURLs;
import com.expenditure.mapper.ExpenditureMapper;
import com.expenditure.service.ExpenditureSevice;

@RestController
public class ExpenditureController {

	@Autowired
	private ExpenditureMapper expenditureMapper;

	@Autowired
	private ExpenditureSevice expenditureSevice;

	
	@PutMapping(ExpenditureURLs.ADD_EXPENSE)
	private ExpensesTo addExpenses(@RequestBody ExpensesTo expensesTo) {

		return expenditureMapper
				.expensesToExpensesTo(expenditureSevice.create(expenditureMapper.expensesToToExpenses(expensesTo)));

	}

}
