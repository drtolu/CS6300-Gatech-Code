package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.helper.DatabaseHelper;
import edu.gatech.seclass.prj2.model.Customer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditCustomer extends Activity {
	private String email;
	private Customer customer;
	
	private EditText  etFirst , etLast, etZip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_customer);
		
		Bundle b = getIntent().getExtras();
		this.email = b.getString("email");
		
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		this.customer=db.getCustomer(email);
		/*
		 * intilize all text boxes in this screen
		 */
		TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
		etFirst = (EditText) findViewById(R.id.etEditFirstName);
		etLast = (EditText) findViewById(R.id.etEditLastName);
		etZip = (EditText) findViewById(R.id.etEditZip);
		
		Button btn =(Button) findViewById(R.id.btnEditCustomer);
		
		if (customer!=null) {
			txtEmail.setText(email);
			etFirst.setText(customer.getFirstName());
			etLast.setText(customer.getLastName());
			etZip.setText(customer.getZipCode());
		}
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_customer, menu);
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
	
	public void editCustomer(View v) {
		Button btn = (Button) v;
		switch (btn.getId()) {
    		case R.id.btnEditCustomer:
    			if (!email.equals("")){
    				Customer cust = new Customer(
    						String.valueOf(email),
    						String.valueOf(etFirst.getText()),
    						String.valueOf(etLast.getText()),
    						String.valueOf(etZip.getText()),
    						this.customer.getGoldStatus(),
    						this.customer.getRewardStatus()
    						);
    				new DatabaseHelper(getApplicationContext()).updateCustomer(cust, email);
    				Toast.makeText(this, "Your edit was successful!", Toast.LENGTH_SHORT).show();
    				finish();
			}
			break;
		default:
			//some weird button
			break;
		}
	}
}
