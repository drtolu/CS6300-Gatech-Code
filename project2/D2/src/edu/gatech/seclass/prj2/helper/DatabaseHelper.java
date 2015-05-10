/* This class has been structured based on a tutorial available at this link - 
 * http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/*
 * 
 */
package edu.gatech.seclass.prj2.helper;

import edu.gatech.seclass.prj2.model.Transaction;
import edu.gatech.seclass.prj2.model.Customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String LOG                        = DatabaseHelper.class.getName();

	// Database Version
	private static final int DATABASE_VERSION 			   = 1;

	// Database Name
	private static final String DATABASE_NAME              = "StallManagerDB";

	// Error Codes
	private static final int CUSTOMER_UPDATE_SUCCESS       = 1;
	private static final int CUSTOMER_UPDATE_ERROR         = 0;

	// Table Names
	private static final String TABLE_TRANSACTION          = "trans";
	private static final String TABLE_CUSTOMER             = "customer";

	// Common column names
	private static final String KEY_ID                     = "szEmailID";

	// Transaction Table - column names
	private static final String KEY_EMAILID                = "szEmailID";
	private static final String KEY_DATE                   = "szDate";
	private static final String KEY_AMOUNT                 = "dAmount";
	private static final String KEY_CASHDISCOUNT           = "dCashDiscount";
	private static final String KEY_GOLDDISCOUNT 		   = "dGoldDiscount";

	// Customer Table - column names
	private static final String KEY_FIRSTNAME              = "szFirstName";
	private static final String KEY_LASTNAME               = "szLastName";
	private static final String KEY_ZIPCODE                = "szZipCode";
	private static final String KEY_GOLDSTATUS             = "bGoldStatus";
	private static final String KEY_REWARDSTATUS		   = "bRewardStatus";
	
	private static final String KEY_TOTAL_YTD 			   = "totalYTD"; 

	// Table Create Statements
	// Transaction table create statement
	private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE "
			+ TABLE_TRANSACTION + "(" + KEY_EMAILID + " TEXT, " + KEY_DATE
			+ " TEXT, " + KEY_AMOUNT + " DOUBLE, " + KEY_CASHDISCOUNT
			+ " DOUBLE, " +  KEY_GOLDDISCOUNT + " DOUBLE " + ")";

	private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE "
			+ TABLE_CUSTOMER + "(" + KEY_ID + " TEXT PRIMARY KEY," 
			+ KEY_FIRSTNAME	+ " TEXT," + KEY_LASTNAME+ " TEXT," 
			+ KEY_ZIPCODE + " TEXT,"  + KEY_GOLDSTATUS + " INTEGER," 
			+ KEY_REWARDSTATUS + " INTEGER" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_CUSTOMER);
		db.execSQL(CREATE_TABLE_TRANSACTION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);

		// create new tables
		onCreate(db);
	}

	// ------------------------ "transaction" table methods ----------------//

	/** 
	 * Create a transaction
	 * @param transaction
	 * @return transaction id
	 */
	public long createTransaction(Transaction transaction) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAILID, transaction.getEmailID());
		values.put(KEY_DATE, transaction.getDate());
		values.put(KEY_AMOUNT, transaction.getAmount());
		values.put(KEY_CASHDISCOUNT, transaction.getCashDiscount());
		values.put(KEY_GOLDDISCOUNT, transaction.getGoldDiscount());

		// insert row
		long transaction_id = db.insert(TABLE_TRANSACTION, "", values);

		return transaction_id;
	}
	
	/**
	 * Get latest transaction for a user.
	 * @param emailID
	 * @return the customer's transaction with the most recent timestamp
	 */
	public Transaction getLatestTransaction(String emailID) {
		Transaction tx = new Transaction();
		String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION 
				+ " WHERE " + KEY_EMAILID + " = \"" + emailID + "\" AND "  
				+ KEY_DATE + " = (SELECT MAX(" + KEY_DATE + ") FROM " + TABLE_TRANSACTION
					+ " WHERE " + KEY_EMAILID + " = \"" + emailID + "\")";

		Log.e(LOG, selectQuery);
				
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null && c.moveToFirst()) {
			tx.setEmailID(c.getString((c.getColumnIndex(KEY_EMAILID))));
			tx.setDate((c.getString(c.getColumnIndex(KEY_DATE))));
			tx.setAmount(c.getDouble(c.getColumnIndex(KEY_AMOUNT)));
			tx.setCashDiscount(c.getDouble(c.getColumnIndex(KEY_CASHDISCOUNT)));
			tx.setGoldDiscount(c.getDouble(c.getColumnIndex(KEY_GOLDDISCOUNT)));
		}
		
		return tx;
	}


	/**
	 *  Get all previous transactions for a user.
	 * @param emailID
	 * @return empty array if no records found. Otherwise an array of transactions.
	 */
	public List<Transaction> getAllTransactions(String emailID) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION + " WHERE " + KEY_EMAILID + " = \"" + emailID +"\"";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c != null && c.moveToFirst()) {
			do {
				Transaction tx = new Transaction();
				tx.setEmailID(c.getString((c.getColumnIndex(KEY_EMAILID))));
				tx.setDate((c.getString(c.getColumnIndex(KEY_DATE))));
				tx.setAmount(c.getDouble(c.getColumnIndex(KEY_AMOUNT)));
				tx.setCashDiscount(c.getDouble(c.getColumnIndex(KEY_CASHDISCOUNT)));
				tx.setGoldDiscount(c.getDouble(c.getColumnIndex(KEY_GOLDDISCOUNT)));

				// adding to transaction list
				transactions.add(tx);
			} while (c.moveToNext() && c != null);
		}

		/*for (Transaction tran : transactions)
		{
			System.out.println("\n\n\nEmail ID :" + tran.getEmailID() + "\nDate :" + tran.getDate() + "\nAmount :"+tran.getAmount() + "\nCashDiscount :" + 
		                       tran.getCashDiscount() + "\nGoldDiscount :"+tran.getGoldDiscount());		
		}*/

		return transactions;
	}

	// ------------------------ "customer" table methods ----------------//

	/** Creating a Customer 
	 * 
	 * @param customer
	 * @return 1 for success and 0 for failure.
	 */
	public long createCustomer(Customer customer) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, customer.getEmailID());
		values.put(KEY_FIRSTNAME, customer.getFirstName());
		values.put(KEY_LASTNAME, customer.getLastName());
		values.put(KEY_ZIPCODE, customer.getZipCode());
		values.put(KEY_GOLDSTATUS, customer.getGoldStatus());
		values.put(KEY_REWARDSTATUS, customer.getRewardStatus() );

		// insert row
		try{
			db.insert(TABLE_CUSTOMER, null, values);
		}catch (Exception e) {
			return CUSTOMER_UPDATE_ERROR;
		}

		return CUSTOMER_UPDATE_SUCCESS;


	}

	/** Getting a Customer
	 * 
	 * @param emailID
	 * @return an empty customer object if not found. Otherwise, details of a particular customer
	 */

	public Customer getCustomer(String emailID) {

		String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER + " WHERE " + KEY_ID + " = \'" + emailID + "\'";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		Customer cust = new Customer();

		if (c != null)
		{
			c.moveToFirst();

			cust.setEmailID(c.getString((c.getColumnIndex(KEY_ID))));
			cust.setFirstName((c.getString(c.getColumnIndex(KEY_FIRSTNAME))));
			cust.setLastName(c.getString(c.getColumnIndex(KEY_LASTNAME)));
			cust.setZipCode(c.getString(c.getColumnIndex(KEY_ZIPCODE)));
			cust.setGoldStatus(c.getInt(c.getColumnIndex(KEY_GOLDSTATUS)) != 0);
			cust.setRewardStatus(c.getInt(c.getColumnIndex(KEY_REWARDSTATUS)) != 0);
		}

		return cust;
	}

	/** Editing a Customer
	 * 
	 * @param customer
	 * @param szOrigEmailID
	 * @return 1 for success. 0 for failure
	 */

	public int updateCustomer(Customer customer, String szOrigEmailID) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(KEY_ID, customer.getEmailID());
		values.put(KEY_FIRSTNAME, customer.getFirstName());
		values.put(KEY_LASTNAME, customer.getLastName());
		values.put(KEY_ZIPCODE, customer.getZipCode());
		values.put(KEY_GOLDSTATUS, customer.getGoldStatus() ? 1 : 0);
		values.put(KEY_REWARDSTATUS, customer.getRewardStatus() ? 1 : 0);

		// updating row
		try{
			db.update(TABLE_CUSTOMER, values, KEY_ID + " = ?",
					new String[] { String.valueOf(szOrigEmailID) });
		}catch (Exception e) {
			return CUSTOMER_UPDATE_ERROR;
		}

		if (!szOrigEmailID.equals(customer.getEmailID()))
		{
			ContentValues values_tran = new ContentValues();
			values_tran.put(KEY_EMAILID, customer.getEmailID());

			db.update(TABLE_TRANSACTION, values_tran, KEY_EMAILID + " = ?",
					new String[] { String.valueOf(szOrigEmailID) }); 		
		} 			

		return CUSTOMER_UPDATE_SUCCESS;

	}


	
	
	//return all customer emails
	public List<String> getAllCustomers() {
		
	    List<String> List = new ArrayList<String>();
	    
	    // Select All Query
	    String selectQuery = "SELECT  "+ KEY_ID + " FROM " + TABLE_CUSTOMER;
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            List.add(cursor.getString(0));
	        } while (cursor.moveToNext());
	    }
	    
	    return List;
	}
	
	
	
	//return a map of amount and date of each transaction
	public List<Map<String, String>> getCustTransFormatted(String email) {
		

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

	    String selectQuery = "SELECT  " + KEY_DATE  + ","  + KEY_AMOUNT + " FROM " + TABLE_TRANSACTION + " WHERE "
	    		+ KEY_EMAILID + " = '" + email + "' order by 1 desc";	
	    
	    Log.e(LOG, selectQuery);
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	        	Map<String, String> datum = new HashMap<String, String>(2);
    	        	datum.put("Date", cursor.getString(0));
    	            datum.put("Amount", "$" + cursor.getString(1));
    	            list.add(datum);

	        } while (cursor.moveToNext());
	    }
	    
	    return list;
	}
	
	
	public double getCustomerTotalYTD(String email) {
		Calendar cal = Calendar.getInstance(); 
		int year = cal.get(Calendar.YEAR);
		cal.set(year, 0, 1);
		
		String begOfYear = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
		
		String selectQuery = "SELECT SUM(" + KEY_AMOUNT + ") AS " + KEY_TOTAL_YTD + " FROM " + TABLE_TRANSACTION 
				+ " WHERE " + KEY_DATE + " > '" + begOfYear +"'";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		double ytdTotal = 0;
		// looping through all rows and adding to list
		if (c != null && c.moveToFirst()) {
			do {
				ytdTotal = c.getDouble(c.getColumnIndex(KEY_TOTAL_YTD));
			} while (c.moveToNext() && c != null);
		}
		
		return ytdTotal;
	}
		
	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

}