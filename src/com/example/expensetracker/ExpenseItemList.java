package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

public class ExpenseItemList {
	
	protected ArrayList<ExpenseItem> expenseItemList;
	protected ArrayList<Listener> listeners;
	
	public ExpenseItemList() {
		expenseItemList = new ArrayList<ExpenseItem>();
		listeners = new ArrayList<Listener>();
	}
	
	public Collection<ExpenseItem> getExpenseItems() {
		return expenseItemList;
	} 

	public void addExpenseItem(ExpenseItem testExpenseItem) {
		expenseItemList.add(testExpenseItem);
		notifyListener();
	}

	private void notifyListener() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}
	
	public void removeExpenseItem(ExpenseItem testExpenseItem) {
		expenseItemList.remove(testExpenseItem);
		notifyListener();
		
	}
	
	public void addListener(Listener l) {
		listeners.add(l);
	}
	
	public void removeListener(Listener l) {
		listeners.remove(l);
	}

}
