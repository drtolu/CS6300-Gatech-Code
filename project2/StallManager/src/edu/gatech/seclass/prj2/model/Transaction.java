/* This class has been structured based on a tutorial available at this link - 
 * http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/*
 * 
 */

package edu.gatech.seclass.prj2.model;

public class Transaction {
	private String szEmailID; // Application is responsible to prevent SQL injection attack by making sure that the email id entered by the user does not contain special characters
	private String szDate; //Please convert the DateTime object for the transaction into a string before storing it in the database. Beware of SQL injection attack.
	private double dAmount;
	private double dCashDiscount; //Value of the cash discount for this transaction. 0 if not applicable.
	private double dGoldDiscount; //Value of the gold discount for this transaction. 0 if not applicable.
	
    // constructors
    public Transaction() {
    }
 
    public Transaction(String szEmailID, String szDate, double dAmount,
    		           double dCashDiscount, double dGoldDiscount) {
        this.szEmailID = szEmailID;
        this.szDate    = szDate;
        this.dAmount   = dAmount;
        this.dCashDiscount = dCashDiscount;
        this.dGoldDiscount = dGoldDiscount;
    }
 
    // setters
    public void setEmailID(String szEmailID) {
        this.szEmailID = szEmailID;
    }
 
    public void setDate(String szDate) {
        this.szDate = szDate;
    }
 
    public void setAmount(double dAmount) {
        this.dAmount = dAmount;
    }
     
    public void setCashDiscount(double dCashDiscount){
        this.dCashDiscount = dCashDiscount;
    }
    
    public void setGoldDiscount(double dGoldDiscount){
        this.dGoldDiscount = dGoldDiscount;
    }
 
    // getters
    public String getEmailID() {
        return this.szEmailID;
    }
 
    public String getDate() {
        return this.szDate;
    }
 
    public double getAmount() {
        return this.dAmount;
    }
    
    public double getCashDiscount() {
    	return this.dCashDiscount;
    }
    
    public double getGoldDiscount() {
    	return this.dGoldDiscount;
    }
 }
