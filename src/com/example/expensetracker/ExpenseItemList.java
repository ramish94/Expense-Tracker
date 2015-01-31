package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

public class ExpenseItemList {
	
	protected ArrayList<ExpenseItem> expenseItemList;
	
	public ExpenseItemList() {
		expenseItemList = new ArrayList<ExpenseItem>();
	}
	
	public Collection<ExpenseItem> getExpenseItems() {
		return expenseItemList;
	} 

	public void addExpenseItem(ExpenseItem testExpenseItem) {
		expenseItemList.add(testExpenseItem);
		notifyListener();
	}

	private void notifyListener() {
		// TODO Auto-generated method stub
		
	}

	public void removeExpenseItem(ExpenseItem testExpenseItem) {
		expenseItemList.remove(testExpenseItem);
		
	}

}
