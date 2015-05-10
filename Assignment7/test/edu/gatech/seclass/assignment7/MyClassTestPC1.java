package edu.gatech.seclass.assignment7;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyClassTestPC1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPC1TT()
	{
		MyClass mc = new MyClass();

		int r1 = mc.buggyMet1(5, true, true);
		assertEquals(1, r1);
	}
	
	@Test
	public void testPC1TF()
	{
		MyClass mc = new MyClass();

		int r2 = mc.buggyMet1(5, true, false);
		assertEquals(0, r2);
	}
	
	@Test
	public void testPC1FF()
	{
		MyClass mc = new MyClass();

		int r3 = mc.buggyMet1(5, false, false);
		assertEquals(1, r3);
	}
	
	@Test
	public void testPC1FT() throws ArithmeticException{
		
		MyClass mc = new MyClass();

		mc.buggyMet1(5, false, true);		
	}

}
