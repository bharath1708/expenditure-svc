/**
 * 
 */
package com.expenditure.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.expenditure.commons.exceptionmessage.ExceptionMessage;
import com.expenditure.commons.tos.SavingTo;
import com.expenditure.commons.tos.UserTo;
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
	static UserTo test = new UserTo();
	static SavingTo saving = new SavingTo();
	static {
		YearMonth ym = YearMonth.now();
		BigDecimal bd1 = new BigDecimal(2000000.01);
		saving.setYearMonth(ym);
		saving.setToatalSavingAmount(bd1);
		test.setSavings(new ArrayList<SavingTo>(Arrays.asList(saving)));
	}

	@Override
	public Expenses create(Expenses expenses) {
		expenses.setCreatedBy(userName);
		expenses.setExpenseId(UUID.randomUUID().toString());
		inMemory.add(expenses);
		YearMonth ym = YearMonth.now();
		Optional<SavingTo> ispreserntCheck = test.getSavings().stream().filter(
				x -> x.getYearMonth().getMonth().equals(ym.getMonth()) && (x.getYearMonth().getYear() == ym.getYear()))
				.findAny();
		if (ispreserntCheck.isPresent()) {
			System.out.println("Inside");
			ispreserntCheck.get().setToatalSavingAmount(
					ispreserntCheck.get().getToatalSavingAmount().subtract(expenses.getExpensedAmount()));

			System.out.println(
					"Inside" + ispreserntCheck.get().getToatalSavingAmount().subtract(expenses.getExpensedAmount()));
		} else {

			test.getSavings().add(new SavingTo(ym, test.getTotalSalary()));
		}

		test.getSavings().forEach(x -> {
			System.out.println(">>>>>>>" + x.getToatalSavingAmount() + "  " + ">>>>>>>>>>>>>>>>>>" + x.getYearMonth());
		});
		return expenses;
	}

	@Override
	public List<Expenses> getAllExpenses() {

		return inMemory;
	}

	@Override
	public Expenses getExpense(String expenseId) throws ExpenseNotFound {

		Optional<Expenses> expense = inMemory.stream().filter(x -> x.getExpenseId().equals(expenseId)).findFirst();

		if (expense.isPresent()) {

			return expense.get();
		} else {
			throw new ExpenseNotFound(ExceptionMessage.EXPENSE_NOT_FOUND);
		}

	}

}
