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
