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
 * Claim.java
 * 
 * This is the class that allows us to create the Claim object and utilize OOP. Characteristics include claimName, claimStartDate.
 * claimEndDate, and claimDescription. We initialize the attributes of the Claim object
 * 
 * Design Rationale: We needed a class for Claim if we wanted to go through the Object-Oriented version of this project. Will be used
 * in other classes for storing, and serializing data. 
 * 
 * No outstanding issues.
 */

package com.example.expensetracker;

import java.io.Serializable;

public class Claim implements Serializable {

	/**
	 * Claim serialization ID
	 */
	
	private static final long serialVersionUID = 7723258797320542815L;
	protected String claimName;
	protected String claimStartDate;
	protected String claimEndDate;
	protected String claimDescrpition;

	public Claim(String claimName, String claimStartDate, String claimEndDate, String claimDescription) {
    	this.claimName = claimName;
        this.claimStartDate = claimStartDate;
        this.claimEndDate = claimEndDate;
        this.claimDescrpition = claimDescription;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return claimName;
	}

	public String getStartDate() {
		// TODO Auto-generated method stub
		return claimStartDate;
	}
	
	public String getEndDate() {
		// TODO Auto-generated method stub
		return claimEndDate;
	}
	
	public String getDescription() {
		// TODO Auto-generated method stub
		return claimDescrpition;
	}
	
	public String toString() {
		return claimName + "\n" + "Dated from " + claimStartDate + " to " + claimEndDate + "\n" + claimDescrpition + 
				"\n" + "\n" + "Total Claim Amount: ";
	}
	
	public boolean equals(Object compareClaim) {
		if (compareClaim != null && compareClaim.getClass() == this.getClass()) {
			return this.equals((Claim) compareClaim);
		} else {
			return false;
		}
	}
	
	public boolean equals(Claim compareClaim) {
		if (compareClaim == null) {
			return false;
		}
		return getName().equals(compareClaim.getName());
	}
	
	public int hashCode() {
		return ("Claim name: "+getName()).hashCode();
	}

}
