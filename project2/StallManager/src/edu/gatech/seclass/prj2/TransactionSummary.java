package edu.gatech.seclass.prj2;

import java.text.DecimalFormat;

import edu.gatech.seclass.prj2.helper.DatabaseHelper;
import edu.gatech.seclass.prj2.model.Customer;
import edu.gatech.seclass.prj2.model.Transaction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TransactionSummary extends Activity {
	
	private String email;
	private Transaction transaction;
	private Customer customer;
	
	private TextView customerName, transactionAmount, todaysDate, message, originalAmount;
	DecimalFormat precision = new DecimalFormat("0.00");
	
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
		message 			= (TextView) findViewById(R.id.tvMsg);
		originalAmount		= (TextView) findViewById(R.id.tvOriginal);
		
		
		if (transaction!=null) {
			customerName.setText(Html.fromHtml("<html><strong>Customer: </strong></html>" + customer.getFirstName() + " " + customer.getLastName()));
			transactionAmount.setText(Html.fromHtml("<html><strong>Amount: $</strong></html>" + (precision.format(Double.parseDouble(String.valueOf(transaction.getAmount()))))));
			todaysDate.setText(Html.fromHtml("<html><strong>Date: </strong></html> " +  transaction.getDate()));
			message.setText(Html.fromHtml("<Html><p>Congratulations!</p> <p>You successfully processed this customer&apos;s transaction!</p>"));
			/*
			 * This isn't correct, but I think this should be on there. We need to show how much is saved
			 */
			originalAmount.setText(Html.fromHtml("<html><strong>You saved: $</strong></html> " +  precision.format(Double.parseDouble(String.valueOf(transaction.getGoldDiscount() + transaction.getCashDiscount())))));
			
		}
		db.close();
	}
	
	public void returnToMainMenu(View v) {
		Intent mainIntent = new Intent(this, MainActivity.class);
		mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
	}
	
}
