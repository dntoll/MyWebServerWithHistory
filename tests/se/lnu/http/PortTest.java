package se.lnu.http;

import org.junit.Test;

public class PortTest {

	

	@Test(expected=InvalidPortException.class)
	public void testPort0() throws InvalidPortException {
		Port p = new Port(0);
	}
	@Test(expected=InvalidPortException.class)
	public void testPortTooLarge() throws InvalidPortException {
		Port p = new Port(65535 + 1);
	}
	
	@Test
	public void testPortOk() throws InvalidPortException {
		Port p = new Port(80);
		
		//no exception
	}

}
