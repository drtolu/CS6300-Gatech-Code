package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.helper.DatabaseHelper;
import edu.gatech.seclass.prj2.model.Customer;
import edu.gatech.seclass.prj2.model.Transaction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TransactionSummary extends Activity {
	
	private String email;
	private Transaction transaction;
	private Customer customer;
	
	private TextView customerName, transactionAmount, todaysDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_summary);
		
		Bundle b 			= getIntent().getExtras();
		this.email			= b.getString("email");
		
		DatabaseHelper db 	= new DatabaseHelper(getApplicationContext());
		this.transaction	= db.getLatestTransaction(email);
		this.customer 		= db.getCustomer(email);
		
		/*
		 * initialize all text boxes in this screen
		 */
		customerName 		= (TextView) findViewById(R.id.customerName);
		transactionAmount	= (TextView) findViewById(R.id.transactionAmount);
		todaysDate 			= (TextView) findViewById(R.id.todaysDate);
		
		if (transaction!=null) {
			customerName.setText(customer.getFirstName() + " " + customer.getLastName());
			transactionAmount.setText(Double.toString(transaction.getAmount()));
			todaysDate.setText(transaction.getDate());
		}
		db.close();
	}
	
	public void returnToMainMenu(View v) {
		Intent mainIntent = new Intent(this, MainActivity.class);
		mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
	}
	
}
