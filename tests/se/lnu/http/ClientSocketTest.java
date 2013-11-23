package se.lnu.http;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientSocketTest {

	private Socket socket;
	private ClientSocket sut;
	String expected = "some data\r\nother stuff\r\n\r\n";

	@Before
	public void setUp() throws Exception {
		socket = mock(Socket.class);
		sut = new ClientSocket(socket);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRequest() throws IOException {
		
		
		
		ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(expected.getBytes());
		when(socket.getInputStream()).thenReturn( byteArrayStream);
		
		String actual = sut.getRequest();
		assertEquals(expected, actual);
	}

	@Test
	public void testWriteResponse() throws IOException {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		when(socket.getOutputStream()).thenReturn( outContent);
		sut.writeResponse(expected);
		
		assertEquals(expected, outContent.toString());
		
		verify(socket).close();
	}

}
