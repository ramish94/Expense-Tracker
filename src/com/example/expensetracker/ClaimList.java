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

public class ClaimList implements Serializable {
	
	/**
	 * ClaimList serialization ID
	 */
	private static final long serialVersionUID = 7082287930769549935L;
	protected ArrayList<Claim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ClaimList() {
		claimList = new ArrayList<Claim>(); 
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null) {
			listeners = new ArrayList <Listener>();
		}
		return listeners;
	}

	public Collection<Claim> getClaims() {
		return claimList;
	} 

	public void addClaim(Claim testClaim) {
		claimList.add(testClaim);
		notifyListener();
	}
	
	public void editClaim(Claim testClaim, int i) {
		claimList.set(i, testClaim);
		notifyListener();
	}

	private void notifyListener() {
		for (Listener listener : getListeners()) {
			listener.update();
		}
	}

	public void removeClaim(Claim testClaim) {
		claimList.remove(testClaim);
		notifyListener();
		
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}

}
