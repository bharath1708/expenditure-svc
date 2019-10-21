/**
 * 
 */
package com.expenditure.serviceimpl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.expenditure.commons.exceptionmessage.ExceptionMessage;
import com.expenditure.exception.ExpenseNotFound;
import com.expenditure.models.Expenses;
import com.expenditure.service.ExpenditureSevice;

/**
 * @author bharath.kk
 *
 */
@Service
public class ExpenditureSeviceImpl implements ExpenditureSevice {

	private List<Expenses> inMemory = new ArrayList<Expenses>();;
	private final String userName = "test-user";

	@Override
	public Expenses create(Expenses expenses) {

		expenses.setCreatedBy(userName);
		expenses.setExpenseId(UUID.randomUUID().toString());
		inMemory.add(expenses);
		return expenses;
	}

	@Override
	public List<Expenses> getAllExpenses() {

		return inMemory;
	}

	@Override
	public Expenses getExpense(String expenseId) throws ExpenseNotFound {
		
		Optional<Expenses> expense=inMemory.stream().filter(x->x.getExpenseId().equals(expenseId)).findFirst();
		
		if(expense.isPresent()) {
			
			return expense.get();
		}else {
			throw new ExpenseNotFound(ExceptionMessage.EXPENSE_NOT_FOUND);
		} 
		

	}

}
