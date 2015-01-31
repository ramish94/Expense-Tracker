package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

public class ClaimList {
	
	protected ArrayList<Claim> claimList;
	
	
	public ClaimList() {
		claimList = new ArrayList<Claim>();
	}

	public Collection<Claim> getClaims() {
		return claimList;
	} 

	public void addClaim(Claim testClaim) {
		claimList.add(testClaim);
	}

	private void notifyListeners() {
		// TODO Auto-generated method stub
	}

	public void removeClaim(Claim testClaim) {
		claimList.remove(testClaim);
		
	}
	public Claim chooseClaim() throws EmptyClaimListException {
		int size = claimList.size();
		if (size <= 0) {
			throw new EmptyClaimListException();
		}
		int index = (int) (claimList.size() * Math.random());
		return claimList.get(index);
	}

	public int size() {
		return claimList.size();
	}

	public boolean contains(Claim testClaim) {
		return claimList.contains(testClaim);
	}
}
