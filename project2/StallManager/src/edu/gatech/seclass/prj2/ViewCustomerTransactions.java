package edu.gatech.seclass.prj2;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.prj2.helper.DatabaseHelper;
import edu.gatech.seclass.prj2.model.Customer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ViewCustomerTransactions extends Activity
{
	private String email;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_customer_transactions);
		Bundle b = getIntent().getExtras();
		this.email = b.getString("email");
		TextView txt = (TextView) findViewById(R.id.txtEmail);
		Customer cust = new DatabaseHelper(getApplicationContext()).getCustomer(this.email);
		txt.setText(MessageFormat.format("View {0} {1}'s Transactions", cust.getFirstName(), cust.getLastName()));
		
		ListView lv =  (ListView) findViewById(R.id.lstTransactions);
		
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		
		List<Map<String, String>> list = db.getCustTransFormatted(email);
		
		SimpleAdapter adapter = new SimpleAdapter(
				 this, list, android.R.layout.simple_list_item_2,
                 new String[] {"Amount", "Date"}, 
                 new int[] {android.R.id.text1, android.R.id.text2}
                );
		 lv.setAdapter(adapter);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_customer_transactions, menu);
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
}
