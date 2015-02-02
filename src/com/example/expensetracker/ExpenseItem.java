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

/*
 *
 * This class does exactly the same thing as Claim.java, but for ExpenseItems. Refer back to Claim.java
 * for comments describing its purpose, design rationale, and any outstanding issues.
 * 
 *
 * REMOVE COMMENTED OUT PORTION OF THE CODE down below (the equals and hashCode methods). Run once more before removing
 *
 *
 */

package com.example.expensetracker;

import java.io.Serializable;

public class ExpenseItem implements Serializable {
	
	/**
	 * ExpenseItem serialization ID
	 */
	private static final long serialVersionUID = -7069571140571193967L;
	
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
	
	/*public boolean equals(Object compareExpenseItem) {
		if (compareExpenseItem != null && compareExpenseItem.getClass() == this.getClass()) {
			return this.equals((Claim) compareExpenseItem);
		} else {
			return false;
		}
	}
	
	public boolean equals(ExpenseItem compareExpenseItem) {
		if (compareExpenseItem == null) {
			return false;
		}
		return getItemDescription().equals(compareExpenseItem.getItemDescription());
		}

	public int hashCode() {
		return ("Expense item description: "+getItemDescription()).hashCode();
	}*/
}
