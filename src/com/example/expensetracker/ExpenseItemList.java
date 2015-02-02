package com.example.expensetracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ExpenseItemList implements Serializable {
	
	/**
	 * ExpenseItemList serialization ID
	 */
	private static final long serialVersionUID = 2258272636220001170L;
	protected ArrayList<ExpenseItem> expenseItemList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ExpenseItemList() {
		expenseItemList = new ArrayList<ExpenseItem>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null) {
			listeners = new ArrayList <Listener>();
		}
		return listeners;
	}
	
	public Collection<ExpenseItem> getExpenseItems() {
		return expenseItemList;
	} 

	public void addExpenseItem(ExpenseItem testExpenseItem) {
		expenseItemList.add(testExpenseItem);
		notifyListener();
	}

	private void notifyListener() {
		for (Listener listener : getListeners()) {
			listener.update();
		}
	}
	
	public void removeExpenseItem(ExpenseItem testExpenseItem) {
		expenseItemList.remove(testExpenseItem);
		notifyListener();
		
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	public void removeListener(Listener l) {
		getListeners().remove(l); 
	}

	public void editExpenseItemList(ExpenseItem expenseItem, int i) {
		expenseItemList.set(i, expenseItem);
		notifyListener();
		
	}

}
