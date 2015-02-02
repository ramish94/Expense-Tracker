package com.example.expensetracker;

import java.io.IOException;

public class ExpenseItemListController {
	
	// Lazy singleton
	private static ExpenseItemList expenseItemList = null;
	
	// WARNING: Throws a runtime exception
	static public ExpenseItemList getExpenseItemList() {
		if (expenseItemList == null) {
			try {
				expenseItemList = ExpenseItemListManager.getManager().loadExpenseItemList();
				expenseItemList.addListener(new Listener() {
					@Override
					public void update() {
						saveExpenseItemList();
					}
				});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ExpenseItemList from ExpenseItemListManager");
			} catch (IOException e) {			
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ExpenseItemList from ExpenseItemListManager");
			}
		}
		return expenseItemList;
	}
	
	static public void saveExpenseItemList() {
		try {
			ExpenseItemListManager.getManager().saveExpenseItemList(getExpenseItemList());
		} catch (IOException e) {			
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ExpenseItemList from ExpenseItemListManager");
		}
	}

	public void addExpenseItem(ExpenseItem expenseItem) {
		// TODO Auto-generated method stub
		getExpenseItemList().addExpenseItem(expenseItem);
		
	}

	public void editExpenseItem(ExpenseItem expenseItem, int i) {
		getExpenseItemList().editExpenseItemList(expenseItem, i);
	}
}
