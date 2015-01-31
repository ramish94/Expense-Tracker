package com.example.expensetracker;

import java.io.Serializable;

public class ExpenseItem {
	
	protected String date;
	protected String category;
	protected String itemDescription;
	protected String amountSpent;
	protected String unitOfCurrency;
	
	public ExpenseItem(String date, String category, String itemDescription, 
			String amountSpent, String unitOfCurrency) {
		this.date = date;
		this.category = category;
		this.itemDescription = itemDescription;
		this.amountSpent = amountSpent;
		this.unitOfCurrency = unitOfCurrency;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	
	public String getAmountSpent() {
		return amountSpent;
	}
	
	public String getUnitOfCurrency() {
		return unitOfCurrency;
	}
	
	public String toString() {
		return itemDescription + "\n" + category + "\n" + date + "\n" + amountSpent + " " + unitOfCurrency + "\n" + "\n";	
	}
}
