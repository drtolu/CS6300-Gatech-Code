package edu.gatech.seclass.assignment7;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyClassTestBC1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBC1() {
		
		MyClass mc = new MyClass();
		
		int r1 = mc.buggyMet1(5, true, true);	
		assertEquals(1, r1);
	}
	
}


