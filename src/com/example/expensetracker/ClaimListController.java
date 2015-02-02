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
 * ClaimListController
 * 
 * Design Rationale: Controller to look over all the functions of ClaimList. Initializes a static null claimList of type ClaimList
 * Serializes data accross the ClaimList using ClaimListManager.
 * 
 * No outstanding issues.
 */

package com.example.expensetracker;

import java.io.IOException;

public class ClaimListController {
	
	// Lazy singleton
	private static ClaimList claimList = null;
	
	// WARNING: Throws a runtime exception
	static public ClaimList getClaimList() {
		if (claimList == null) {
			try {
				claimList = ClaimListManager.getManager().loadClaimList();
				claimList.addListener(new Listener() {
					@Override
					public void update() {
						saveClaimList();
					}
				});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
			} catch (IOException e) {			
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
			}
		}
		return claimList;
	}
	
	static public void saveClaimList() {
		try {
			ClaimListManager.getManager().saveClaimList(getClaimList());
		} catch (IOException e) {			
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
		}
	}

	public void addClaim(Claim claim) {
		getClaimList().addClaim(claim);
		
	}
	
	public void editClaim(Claim claim, int i) {
		getClaimList().editClaim(claim, i);
	}

}
