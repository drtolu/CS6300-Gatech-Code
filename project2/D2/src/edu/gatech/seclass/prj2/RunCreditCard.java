package edu.gatech.seclass.prj2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.seclass.prj2.helper.DatabaseHelper;
import edu.gatech.seclass.prj2.model.Customer;
import edu.gatech.seclass.prj2.model.GoldStatusDiscount;
import edu.gatech.seclass.prj2.model.Reward;
import edu.gatech.seclass.prj2.model.Transaction;
import edu.gatech.seclass.services.*;

public class RunCreditCard extends Activity {

	private String email;
	private String sFirstName = "";
	private String sLastName = "";
	private String sAccountNum = "";
	private String sExpiration = "";
	private String sSecurityCode = "";
	private double dAmount = 0.0;
	private double dGoldAmountOff = 0.0;

	TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

			Button btnSwipe = (Button) findViewById(R.id.btnSwipeCard);

			if (s.toString().trim().length() < 1) {
				btnSwipe.setEnabled(false);
			} else {
				btnSwipe.setEnabled(true);
			}

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run_credit_card);

		TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
		Bundle b = getIntent().getExtras();
		this.email = b.getString("email");

		txtEmail.setText(email);

		EditText etAmount = (EditText) findViewById(R.id.etAmount);

		etAmount.addTextChangedListener(textWatcher);

		Button btnSwipe = (Button) findViewById(R.id.btnSwipeCard);
		Button btnProcess = (Button) findViewById(R.id.btnCheckoutCust);

		btnSwipe.setEnabled(false);
		btnProcess.setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.run_credit_card, menu);

		return true;
	}

	public void btnSwipeCard(View v) {
		String cardInfo = CreditCardService.getCardInfo();

		TextView tvCCnum = (TextView) findViewById(R.id.etCCnum);
		TextView tvSecCode = (TextView) findViewById(R.id.etSecCode);
		TextView tvExpDate = (TextView) findViewById(R.id.etExpDate);
		TextView tvName = (TextView) findViewById(R.id.etName);
		EditText etAmount = (EditText) findViewById(R.id.etAmount);
		Button btnProcess = (Button) findViewById(R.id.btnCheckoutCust);

		if (cardInfo == "Error") {
			Toast.makeText(
					this,
					"There was an error getting your card info, please swipe again",
					Toast.LENGTH_SHORT).show();

			tvCCnum.setText("");
			tvSecCode.setText("");
			tvExpDate.setText("");
			tvName.setText("");

			btnProcess.setEnabled(false);

		}

		else {
			String[] info = cardInfo.split("#");

			sFirstName = info[0];
			sLastName = info[1];
			sAccountNum = info[2];
			sExpiration = info[3];
			sSecurityCode = info[4];
			dAmount = Double.parseDouble(String.valueOf(etAmount.getText()));

			tvCCnum.setText(sAccountNum);
			tvSecCode.setText(sSecurityCode);
			tvExpDate.setText(sExpiration);
			tvName.setText(sFirstName + " " + sLastName);

			btnProcess.setEnabled(true);

		}

	}
	
	private void calculateCustomerDiscount() {
		Customer customer = new DatabaseHelper(getApplicationContext()).getCustomer(this.email);
		
		if (customer.getGoldStatus()) {
			this.dGoldAmountOff = this.dAmount * GoldStatusDiscount.PERCENT_OFF;
			this.dAmount *= (1 - GoldStatusDiscount.PERCENT_OFF);
		}
		
		if (customer.getRewardStatus())
			this.dAmount -= Reward.AMOUNT_OFF;
	}
	
	public boolean cardSuccessfullyProcessed(View v) {
		return PaymentService.processPayment(sFirstName, sLastName,
				sAccountNum, sExpiration, sSecurityCode, dAmount);
	}
	
	private void addCustomerTransactionToDB() {
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		
		Customer customer = db.getCustomer(this.email);
		Transaction transaction = new Transaction();
		
		if (this.dAmount >= Reward.AMOUNT_TO_EARN_REWARD) {
			customer.setRewardStatus(true);
			transaction.setCashDiscount(Reward.AMOUNT_OFF);
		} else {
			customer.setRewardStatus(false);
			transaction.setCashDiscount(0);
		}
		
		if (db.getCustomerTotalYTD(this.email) >= GoldStatusDiscount.AMOUNT_TO_EARN_GOLD_STATUS) {
			customer.setGoldStatus(true);
			transaction.setGoldDiscount(this.dGoldAmountOff);
		} else {
			transaction.setGoldDiscount(0);
		}
		
		transaction.setEmailID(this.email);
		transaction.setAmount(this.dAmount);
		transaction.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		
		db.updateCustomer(customer, this.email);
		db.createTransaction(transaction);
		db.close();
	}

	public void btnProcessCard(View v) {
		calculateCustomerDiscount();
		
		if (cardSuccessfullyProcessed(v)) {

			addCustomerTransactionToDB();

			Intent mainIntent = new Intent(this, TransactionSummary.class);
			mainIntent.putExtra("email", this.email);
			startActivity(mainIntent);

			Toast.makeText(this, "Card Processed Successfully!!!",
					Toast.LENGTH_SHORT).show();
			finish();

		} else {
			Toast.makeText(
					this,
					"There was an error processing your card, please try again",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
