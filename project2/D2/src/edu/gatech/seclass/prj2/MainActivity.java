package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.helper.DatabaseHelper;
import edu.gatech.seclass.prj2.model.Customer;
import edu.gatech.seclass.prj2.model.Transaction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends Activity {
	private int[] buttons  = {R.id.btnManageCust, R.id.btnAddCust};
	private DatabaseHelper db ;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * intilize all buttons in this screen
		 */
		for (int button : buttons) 	{
			Button btn = (Button) findViewById(button);
		}
		
		db = new DatabaseHelper(getApplicationContext());
		
/*Customer customer1 = new Customer("namitxyz@gmail.com",
				"Namit",
				"Gupta",
				"10022",
				"07/15",
				"059",
				12345678,
				20.23,
				5000,
				1);

		Customer customer2 = new Customer("amitxyz@gmail.com",
				"amit",
				"kumar",
				"11104",
				"09/19",
				"098",
				44345678,
				30.23,	
				6000,
				0);
		Transaction transaction1 = new Transaction(	"namitxyz@gmail.com",
				"02142015",
				1000,
				20,
				40);

		Transaction transaction2 = new Transaction("amitxyz@gmail.com",
				"04192019",
				5,
				0,
				0);

		Transaction transaction3 = new Transaction("namitxyz@gmail.com",
				"03142015",
				2000,
				50.55,
				40.21);

		long id = db.createTransaction(transaction1);

		Log.d("Transaction Count", "Transaction Count: " + db.getAllTransactions(transaction1.getEmailID()).size());

		id = db.createTransaction(transaction3);

		Log.d("Transaction Count", "Transaction Count: " + db.getAllTransactions(transaction3.getEmailID()).size());

		id = db.createCustomer(customer1);

		Log.d("Customer name", "customer Balance: " + db.getCustomer(customer1.getEmailID()).getBalanceCashDiscount());

		customer1.setFirstName("Michael");

		id = db.updateCustomer(customer1, "namitxyz@gmail.com");

		Log.d("Customer name after update", "customer Balance: " + db.getCustomer(customer1.getEmailID()).getBalanceCashDiscount());

	    id = db.createTransaction(transaction2);
		id = db.createCustomer(customer2);

		id = db.updateCustomer(customer1, "amitxyz@gmail.com");

		Log.d("Transaction Count", "Transaction Count: " + db.getAllTransactions("namitxyz@gmail.com").size());*/


		db.closeDB(); 

	}

	/*
	 * handles the button clicks
	 */
	public void btnClick(View v){
		Button btn = (Button) v;
		switch (btn.getId()) {
			case R.id.btnAddCust:
				Intent mainIntent = new Intent(this, AddCustomer.class);
                startActivity(mainIntent);
				break;
			case R.id.btnManageCust:
				mainIntent = new Intent(this, ViewCustomers.class);
                startActivity(mainIntent);
                break;
			default:
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
