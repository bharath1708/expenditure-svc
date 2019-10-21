package com.expenditure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ExpenseNotFound extends Exception {

	public ExpenseNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExpenseNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
