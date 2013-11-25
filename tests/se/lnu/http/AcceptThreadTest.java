package se.lnu.http;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AcceptThreadTest {

	private AcceptThread sut;
	private HTTPServerObserver observer;
	private ServerSocket sock;
	private ClientFactory factory;

	@Before
	public void setUp() throws Exception {
		sock = mock(ServerSocket.class);
		observer = mock(HTTPServerObserver.class);
		factory = mock(ClientFactory.class);
		sut = new AcceptThread(sock, observer, factory);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStopmeNotRunning() throws IOException {
		sut.stopme();
	}
	
	@Test
	public void testrun() throws IOException {
	    Socket socket = mock(Socket.class);
	    ClientThread thread = mock(ClientThread.class);
		when(sock.accept()).thenReturn(socket).thenThrow(new SocketException());
		
		
		when(factory.createClient(socket)).thenReturn(thread);
		sut.run();
		
		verify(sock, times(2)).accept();
		
		verify(thread).start();
		
		
	}
	
	@Test
	public void testsocketfailed() throws IOException {
	    Socket socket = mock(Socket.class);
	    ClientThread thread = mock(ClientThread.class);
		when(sock.accept()).thenThrow(new IOException()).thenThrow(new SocketException());
		
		sut.run();
		
		verify(thread, never()).start();
		
		
	}
	

}
