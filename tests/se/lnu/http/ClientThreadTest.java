package se.lnu.http;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lnu.http.response.HTTPResponse;

public class ClientThreadTest {

	private ClientSocket sock;
	private ResponseFactory factory;
	private ClientThread sut;

	@Before
	public void setUp() throws Exception {
		sock = mock(ClientSocket.class);
		factory = mock(ResponseFactory.class);
		sut = new ClientThread(sock, factory);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() throws IOException {
		HTTPResponse response = mock(HTTPResponse.class);
		
		when(sock.getRequest()).thenReturn("GET / HTTP1.1\r\n");
		when(factory.getResponse(any(HTTPRequest.class))).thenReturn(response);
		
		sut.run();
		
		verify(response).writeResponse(sock);
	}
	
	
}
