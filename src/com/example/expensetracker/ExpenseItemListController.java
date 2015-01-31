package com.example.expensetracker;

public class ExpenseItemListController {
	
	// Lazy singleton
	private static ExpenseItemList expenseItemList = null;
	
	static public ExpenseItemList getExpenseItemList() {
		if (expenseItemList == null) {
			expenseItemList = new ExpenseItemList(); 
		}
		return expenseItemList;
	}

	public void addExpenseItem(ExpenseItem expenseItem) {
		// TODO Auto-generated method stub
		getExpenseItemList().addExpenseItem(expenseItem);
		
	}
}
