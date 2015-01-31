package com.example.expensetracker;

public class ClaimListController {
	
	// Lazy singleton
	private static ClaimList claimList = null;
	
	static public ClaimList getClaimList() {
		if (claimList == null) {
			claimList = new ClaimList(); 
		}
		return claimList;
	}
	
	/*public Claim chooseClaim() throws EmptyClaimListException {
		return getClaimList().chooseClaim();
	}*/

	public void addClaim(Claim claim) {
		// TODO Auto-generated method stub
		getClaimList().addClaim(claim);
		
	}

}
