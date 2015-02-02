/*	
 * 	Expense Tracker: An application that tracks expense reports and items.
    Copyright (C) 2015 Ramish Syed

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
