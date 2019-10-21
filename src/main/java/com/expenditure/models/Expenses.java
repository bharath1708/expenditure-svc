/**
 * 
 */
package com.expenditure.models;

import java.util.Currency;
import java.util.Date;

import com.expenditure.commons.enums.ExpenseType;

/**
 * @author bharath.kk
 *
 */
public class Expenses {

	private String expenseId;
	private Date expensedOn;
	private Date CreatedOn;
	private Date modifiedon;
	private String createdBy;
	private Currency expensedAmount;
	private ExpenseType expenseType;
	
	/**
	 * @return the expenseId
	 */
	public String getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the expensedOn
	 */
	public Date getExpensedOn() {
		return expensedOn;
	}
	/**
	 * @param expensedOn the expensedOn to set
	 */
	public void setExpensedOn(Date expensedOn) {
		this.expensedOn = expensedOn;
	}
	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return CreatedOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}
	/**
	 * @return the modifiedon
	 */
	public Date getModifiedon() {
		return modifiedon;
	}
	/**
	 * @param modifiedon the modifiedon to set
	 */
	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the expensedAmount
	 */
	public Currency getExpensedAmount() {
		return expensedAmount;
	}
	/**
	 * @param expensedAmount the expensedAmount to set
	 */
	public void setExpensedAmount(Currency expensedAmount) {
		this.expensedAmount = expensedAmount;
	}
	/**
	 * @return the expenseType
	 */
	public ExpenseType getExpenseType() {
		return expenseType;
	}
	/**
	 * @param expenseType the expenseType to set
	 */
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	

}
