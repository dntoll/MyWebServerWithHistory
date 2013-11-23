package se.lnu.http;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import se.lnu.http.exceptions.NotStartedException;


public class HTTPServer {

	
	private HTTPServerObserver view;
	
	private Port port;
	private AcceptThread acceptThread;

	private SharedFolder sharedDirectory;

	public HTTPServer(Port port, SharedFolder sharedDirectory, HTTPServerObserver view) {
		this.view = view;
		view.serverConstructed();
		this.port = port;
		this.sharedDirectory = sharedDirectory;
	}

	public void start() throws IOException {
		ServerSocket socket = new ServerSocket(port.getPort());
		ClientFactory factory = new ClientFactory(this.sharedDirectory);
		acceptThread = new AcceptThread(socket, view, factory);
		
		acceptThread.start();
		
		view.serverStarted();
	}

	public void stop() throws NotStartedException, InterruptedException, IOException {
		if (acceptThread == null)
			throw new NotStartedException();
		
		acceptThread.stopme();
		
		while(acceptThread.isAlive()) {
			Thread.sleep(100);
		}
		
		
		view.serverStopped();
		
		acceptThread = null;
	}

}
