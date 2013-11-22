package se.lnu.http;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HTTPServerTest {

	HTTPServerObserver observer;
	HTTPServer sut;
	private int portNumber = 8080;
	File sharedDirectory = new File("/");
	
	@Before
	public void setUp() throws Exception {
		observer = mock(HTTPServerObserver.class);
		
		sut = new HTTPServer(new Port(portNumber), sharedDirectory, observer);
	}

	@After
	public void tearDown() throws Exception {
		
		try {
			sut.stop();
		} catch(NotStartedException e) {
			//ok
		}
	}

	@Test
	public void testHTTPServer() throws InvalidPortException {
		verify(observer).serverConstructed();
	}

	@Test
	public void testStart() throws IOException {
		verify(observer).serverConstructed();
		sut.start();
		verify(observer).serverStarted();
	}

	@Test
	public void testStop() throws NotStartedException, IOException, InterruptedException {
		
		verify(observer).serverConstructed();
		assertFalse(serverIsOnline());
		sut.start();
		
		assertTrue(serverIsOnline());
		
		sut.stop();
		
		assertFalse(serverIsOnline());
		verify(observer).serverStopped();
	}

	private boolean serverIsOnline() {
		try {
			SocketClient client = new SocketClient(new Socket(), new HTTPGetProtocoll());
			String actual = client.get("127.0.0.1", portNumber , "/crap.php");
			String response = "HTTP/1.1 404 Not Found\r\n";
			
			return actual.contains(response);
		} catch (Exception e) {
			//e.printStackTrace();
		}
			
		
		
		return false;
	}
	
	@Test
	public void testStopWhenNotStarted() throws InterruptedException, IOException {
		verify(observer).serverConstructed();
		try {
			sut.stop();
			fail();
		} catch(NotStartedException e) {
			//ok
		}
		verify(observer, never()).serverStopped();
		
	}

}
