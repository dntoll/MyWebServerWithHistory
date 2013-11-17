package se.lnu.http;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HTTPServerTest {

	HTTPServerObserver observer;
	HTTPServer sut;
	
	@Before
	public void setUp() throws Exception {
		observer = mock(HTTPServerObserver.class);
		sut = new HTTPServer(new Port(80), new File("/"), observer);
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
	public void testStart() {
		verify(observer).serverConstructed();
		sut.start();
		verify(observer).serverStarted();
	}

	@Test
	public void testStop() throws NotStartedException {
		verify(observer).serverConstructed();
		sut.start();
		sut.stop();
		verify(observer).serverStopped();
	}
	
	@Test
	public void testStopWhenNotStarted() {
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
