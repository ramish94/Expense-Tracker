package com.example.expensetracker;

public class Claim {

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
		return claimName + "\n" + "Dated from " + claimStartDate + " to " + claimEndDate + "\n" + claimDescrpition + "\n" + "\n";
	}

}
