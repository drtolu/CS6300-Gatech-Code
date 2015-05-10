package edu.gatech.seclass.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void handleClick(View view)
	{
		EditText szResult = (EditText) findViewById(R.id.result);
		EditText szError =  (EditText) findViewById(R.id.errormsg);
		
		String szData = szResult.getText().toString();
        int iNumberOfOperands = 0;
		
		switch(view.getId())
		{
		case R.id.clear:
			szResult.setText("0");
			szError.setText("");
			  break;
		case R.id.one:
			szResult.setText(szData.equals("0") ? "1" : szData + "1");
			szError.setText("");
			break;
		case R.id.two:
			szResult.setText(szData.equals("0") ? "2" :szData + "2");
			szError.setText("");
			break;
		case R.id.three:
			szResult.setText(szData.equals("0") ? "3" :szData + "3");
			szError.setText("");
			break;
		case R.id.four:
			szResult.setText(szData.equals("0") ? "4" :szData + "4");
			szError.setText("");
			break;
		case R.id.five:
			szResult.setText(szData.equals("0") ? "5" :szData + "5");
			szError.setText("");
			break;
		case R.id.six:
			szResult.setText(szData.equals("0") ? "6" :szData + "6");
			szError.setText("");
			break;
		case R.id.seven:
			szResult.setText(szData.equals("0") ? "7" :szData + "7");
			szError.setText("");
			break;
		case R.id.eight:
			szResult.setText(szData.equals("0") ? "8" :szData + "8");
			szError.setText("");
			break;
		case R.id.nine:
			szResult.setText(szData.equals("0") ? "9" :szData + "9");
			szError.setText("");
			break;
		case R.id.zero:
			szResult.setText(szData.equals("0") ? "0" :szData + "0");
			szError.setText("");
			break;
		case R.id.plus:
			szResult.setText(szData + "+");
			szError.setText("");
			szData = szResult.getText().toString();
						
			for (int i=0; i< szData.length(); i++)
			{
				if (szData.charAt(i) == '+' || szData.charAt(i) == '-' || szData.charAt(i) == '*')
					iNumberOfOperands++;
			}
			
			if (iNumberOfOperands != 1)
			{
				szResult.setText("0");
				szError.setText("Error");			
			}
			break;
		case R.id.minus:
			szResult.setText(szData + "-");
			szError.setText("");
			szData = szResult.getText().toString();
			
			for (int i=0; i< szData.length(); i++)
			{
				if (szData.charAt(i) == '+' || szData.charAt(i) == '-' || szData.charAt(i) == '*')
					iNumberOfOperands++;
			}
			
			if (iNumberOfOperands != 1)
			{
				szResult.setText("0");
				szError.setText("Error");			
			}
			break;
		case R.id.multiply:
			szResult.setText(szData + "*");
			szError.setText("");
			szData = szResult.getText().toString();
			
			for (int i=0; i< szData.length(); i++)
			{
				if (szData.charAt(i) == '+' || szData.charAt(i) == '-' || szData.charAt(i) == '*')
					iNumberOfOperands++;
			}
			
			if (iNumberOfOperands != 1)
			{
				szResult.setText("0");
				szError.setText("Error");			
			}
			
			break;
		case R.id.equals:
						
			for (int i=0; i< szData.length(); i++)
			{
				if (szData.charAt(i) == '+' || szData.charAt(i) == '-' || szData.charAt(i) == '*')
					iNumberOfOperands++;
			}
			
			if (iNumberOfOperands != 1 || (szData.length() > 0 && 
					                       (szData.charAt(szData.length()-1) == '+' ||
					                       szData.charAt(szData.length()-1) == '-' ||
					                       szData.charAt(szData.length()-1) == '*')))
			{
				szResult.setText("0");
				szError.setText("Error");
				return;
			}
			
			for(int i=0;i<szData.length();i++)
			{
			  if(szData.charAt(i)=='+')
			  {
			    int result=Integer.parseInt(szData.substring(0, i))+Integer.parseInt(szData.substring(i+1, szData.length()));
			    szResult.setText(String.valueOf(result));
			  }  
			  
			  if(szData.charAt(i)=='-')
			  {
			    int result=Integer.parseInt(szData.substring(0, i))-Integer.parseInt(szData.substring(i+1, szData.length()));
			    szResult.setText(String.valueOf(result));
			  }  
			  
			  if(szData.charAt(i)=='*')
			  {
			    int result=Integer.parseInt(szData.substring(0, i))*Integer.parseInt(szData.substring(i+1, szData.length()));
			    szResult.setText(String.valueOf(result));
			  }  
			}
						
			break;		
		}
		
	}
}
