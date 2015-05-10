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
import android.widget.Toast;

public class AddCustomer extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_customer);
		int[] txtBoxes= {R.id.etEmail, R.id.etFirstName, R.id.etLastName, R.id.etZip};
		/*
		 * intilize all t in this screen
		 */
		for (int txt : txtBoxes) 	{
			EditText btn = (EditText) findViewById(txt);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.add_customer, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addCustomer(View v){
		Button btn = (Button) v;
		EditText etEmail = (EditText) findViewById(R.id.etEmail);
		EditText etFirst = (EditText) findViewById(R.id.etFirstName);
		
		EditText etLast = (EditText) findViewById(R.id.etLastName);
		EditText etZip = (EditText) findViewById(R.id.etZip);
		
		if (!etEmail.getText().equals("")){
    		if (btn.getId()==R.id.btnSubmit) {
    			new DatabaseHelper(getApplicationContext()).createCustomer(new Customer(
    					String.valueOf(etEmail.getText()),
    					String.valueOf(etFirst.getText()),
    					String.valueOf(etLast.getText()),
    					String.valueOf(etZip.getText()),			
    					false,
    					false)
    			);
    			Toast.makeText(this, "Your customer has been added!", Toast.LENGTH_SHORT).show();
          		Intent mainIntent = new Intent(this, RunCreditCard.class);
           		mainIntent.putExtra("email", String.valueOf(etEmail.getText()));
           		startActivity(mainIntent);
    			
    			finish();
    		}
		}
	}
}
