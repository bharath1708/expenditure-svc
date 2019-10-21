/**
 * 
 */
package com.expenditure.serviceimpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.expenditure.models.Expenses;
import com.expenditure.service.ExpenditureSevice;

/**
 * @author bharath.kk
 *
 */
@Service
public class ExpenditureSeviceImpl implements ExpenditureSevice {

	private ArrayList<Expenses> inMemory = new ArrayList<>();

	@Override
	public Expenses create(Expenses expenses) {
		inMemory.add(expenses);
		return expenses;
	}

}
