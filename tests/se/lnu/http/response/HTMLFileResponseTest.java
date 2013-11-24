package se.lnu.http.response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import se.lnu.http.ClientSocket;

public class HTMLFileResponseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	//http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	@Test
	public void testWriteResponse() throws IOException {
		URL url = this.getClass().getResource("../resources/inner/index.html");
		
		File file = new File(url.getFile());
		
		byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		
		ClientSocket clientSocket = mock(ClientSocket.class);
		HTMLFileResponse response = new HTMLFileResponse(file);
		
		
		response.writeResponse(clientSocket);
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		verify(clientSocket).writeHeader(argument.capture());
		
		ArgumentCaptor<byte[]> bodyArgument = ArgumentCaptor.forClass(byte[].class);
		verify(clientSocket).writeBody(bodyArgument.capture());
		String writtenToSocket = argument.getValue();
		
		assertTrue( writtenToSocket.contains("HTTP/1.1 200 OK\r\n"));
		byte[] actualBytes = bodyArgument.getValue();
		for(int i= 0; i< bytes.length; i++) {
			assertEquals(bytes[i] , actualBytes[i]);
		}
		
		
	}

}
