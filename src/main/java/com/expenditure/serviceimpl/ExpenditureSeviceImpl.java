/**
 * 
 */
package com.expenditure.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		this.checkIfExpensePresent(null, test);
		this.addExpense(this.checkIfExpensePresent(null, test), expenses, null, test);
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

	@Override
	public Expenses update(Expenses expenses) throws ExpenseNotFound {

		Optional<Expenses> expenseOptional = inMemory.stream()
				.filter(x -> x.getExpenseId().equals(expenses.getExpenseId())).findFirst();

		if (expenseOptional.isPresent()) {

			if (!expenseOptional.get().getExpensedAmount().equals(expenses.getExpensedAmount())) {

				this.addExpense(this.checkIfExpensePresent(expenses.getCreatedOn(), test), expenses,
						expenseOptional.get(), test);

			}

			return expenses;
		} else {
			throw new ExpenseNotFound(ExceptionMessage.EXPENSE_NOT_FOUND);
		}
	}

	private Optional<SavingTo> checkIfExpensePresent(Date date, UserTo user) {
		YearMonth ym;
		if (date == null)
			ym = YearMonth.now();
		else
			ym = YearMonth.of(date.getDay(), date.getMonth());

		return user.getSavings().stream().filter(
				x -> x.getYearMonth().getMonth().equals(ym.getMonth()) && (x.getYearMonth().getYear() == ym.getYear()))
				.findAny();

	}

	private void addExpense(Optional<SavingTo> ispreserntCheck, Expenses expenses, Expenses oldExpenses, UserTo user) {

		if (ispreserntCheck.isPresent()) {
			if (oldExpenses == null) {
				ispreserntCheck.get().setToatalSavingAmount(
						ispreserntCheck.get().getToatalSavingAmount().subtract(expenses.getExpensedAmount()));
			} else {
				ispreserntCheck.get().setToatalSavingAmount(ispreserntCheck.get().getToatalSavingAmount()
						.subtract(expenses.getExpensedAmount().add(oldExpenses.getExpensedAmount())));
			}

		} else {
			YearMonth ym = YearMonth.now();
			user.getSavings().add(new SavingTo(ym, user.getTotalSalary()));
		}

	}

}
