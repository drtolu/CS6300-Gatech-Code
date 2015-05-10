package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.helper.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ViewCustomers extends Activity implements OnItemClickListener {
	private ListView lv;
	private String email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_customers);
		lv = (ListView) findViewById(R.id.lstCustomers);
		lv.setOnItemClickListener(this);
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		
		
		 ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                 this, 
                 android.R.layout.simple_list_item_1,
                 db.getAllCustomers() );
		 lv.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_customers, menu);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		email =(String) parent.getItemAtPosition(position);	
		
		PopupMenu pop = new PopupMenu(this, lv);
		pop.getMenuInflater().inflate(R.menu.popup_menu, pop.getMenu());
		pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
            	switch (item.getItemId())  {
            	case R.id.EditCustomerClass:
            		startNewIntent(EditCustomer.class);
            		break;
            	case R.id.RunCreditCardClass:
            		startNewIntent(RunCreditCard.class);
            		break;
            	case R.id.ViewTransactionsClass:
            		startNewIntent(ViewCustomerTransactions.class);
               		break;
            	}
                return true;
            }
        });

        pop.show();
	}
	
	private void startNewIntent(Class className){		
        Intent mainIntent = new Intent(ViewCustomers.this, (className));
		mainIntent.putExtra("email", email);
		startActivity(mainIntent);
	}
}
