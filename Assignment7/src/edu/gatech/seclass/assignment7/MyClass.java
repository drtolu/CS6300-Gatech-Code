package edu.gatech.seclass.assignment7;

public class MyClass {
	
	public int buggyMet1(int a, boolean condition1, boolean condition2)
	{
		int b=a;
		
		if(condition1)
			b = a*2; 
		if(condition2)
			b = b-a;
		
		return a/b; 
	}
	
	public void buggyMet2()
	{
		System.out.println("not possible");
	}
	
	public void buggyMet3()
	{
		System.out.println("not possible");
	}
}
