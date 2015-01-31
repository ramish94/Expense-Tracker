 package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

public class ClaimList {
	
	protected ArrayList<Claim> claimList;
	protected ArrayList<Listener> listeners;
	
	
	public ClaimList() {
		claimList = new ArrayList<Claim>(); 
		listeners = new ArrayList<Listener>();
	}

	public Collection<Claim> getClaims() {
		return claimList;
	} 

	public void addClaim(Claim testClaim) {
		claimList.add(testClaim);
		notifyListener();
	}

	private void notifyListener() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}

	public void removeClaim(Claim testClaim) {
		claimList.remove(testClaim);
		notifyListener();
		
	}
	
	public void addListener(Listener l) {
		listeners.add(l);
	}
	
	public void removeListener(Listener l) {
		listeners.remove(l);
	}

}
