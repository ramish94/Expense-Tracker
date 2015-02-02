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
		// TODO Auto-generated constructor stub
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
