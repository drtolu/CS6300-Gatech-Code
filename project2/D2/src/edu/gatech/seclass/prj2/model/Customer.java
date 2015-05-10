/* This class has been structured based on a tutorial available at this link - 
 * http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/*
 * 
 */

package edu.gatech.seclass.prj2.model;

public class Customer {
	private String  szEmailID; //Application is responsible for input validations, especially to counteract SQL injection attacks.
	private String  szFirstName;
	private String  szLastName;
	private String  szZipCode;
	private boolean	bGoldStatus; // A boolean which can store a value of 0 or 1 indicating if a user is a Gold Status customer or not.
	private boolean	rewardStatus; //  A boolean that can store a value of 0 or 1 indicating if a user has a Reward earned from the last purchase

	// constructors
	public Customer() {
	}

	public Customer(	
			String  szEmailID,
			String  szFirstName,
			String  szLastName,
			String  szZipCode,
			boolean bGoldStatus,
			boolean	rewardStatus) {

		this.szEmailID 		= szEmailID;
		this.szFirstName 	= szFirstName;
		this.szLastName 	= szLastName;
		this.szZipCode 		= szZipCode;
		this.bGoldStatus 	= bGoldStatus;
		this.rewardStatus	= rewardStatus;
		
	}

	// setters
	public void setEmailID(String szEmailID) {
		this.szEmailID = szEmailID;
	}

	public void setFirstName(String szFirstName) {
		this.szFirstName = szFirstName;
	}

	public void setLastName(String szLastName) {
		this.szLastName = szLastName;
	}

	public void setZipCode(String szZipCode) {
		this.szZipCode = szZipCode;
	}

	public void setGoldStatus(boolean bGoldStatus) {
		this.bGoldStatus = bGoldStatus;
	}
	
	public void setRewardStatus(boolean rewardStatus) {
		this.rewardStatus = rewardStatus;
	};

	// getters
	public String getEmailID() {
		return this.szEmailID;
	}

	public String getFirstName() {
		return szFirstName;
	}

	public String getLastName() {
		return szLastName;
	}

	public String getZipCode() {
		return szZipCode;
	}

	public double getBalanceCashDiscount() {
		return 0.0;
	}

	public double getYTDPurchase() {
		return 0.0;
	}

	public boolean getGoldStatus() {
		return bGoldStatus;
	}
	
	public boolean getRewardStatus() {
		return rewardStatus;
	}
}
