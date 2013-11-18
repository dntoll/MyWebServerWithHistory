package se.lnu.http;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.Socket;

import junit.framework.Assert;

import org.junit.Test;


public class SocketClientTest {

	
	/* This test requires both online server and access to internet
	 * */
	@Test
	public void testGetFromOnlineServer() throws Exception {
		
		SocketClient sut = new SocketClient(new Socket(), new HTTPGetProtocoll());
		String actual = sut.get("194.47.172.159", 80, "/");
		
		String expected[] = new String[10];
		expected[0] = "HTTP/1.1 200 OK\r\n";
		expected[1] = "Date: ";
		expected[2] = "Server: Apache/2.2.17 (Win32) mod_wsgi/3.3 Python/2.6.6\r\n";
		expected[3] = "Last-Modified: Sat, 20 Nov 2004 14:16:26 GMT\r\n";
		expected[4] = "ETag: \"100000005f138-2c-3e95144cc6280\"\r\n";
		expected[5] = "Accept-Ranges: bytes\r\n";
		expected[6] = "Content-Length: 44\r\n";
		expected[7] = "Content-Type: text/html\r\n";
		expected[8] = "\r\n";
		expected[9] = "<html><body><h1>It works!</h1></body></html>";
		for(String exp : expected) {
			Assert.assertTrue(actual.contains(exp));
		}
		
	}
}
