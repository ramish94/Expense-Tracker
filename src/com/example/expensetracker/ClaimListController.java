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
