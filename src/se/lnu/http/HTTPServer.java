package se.lnu.http;

import java.io.File;
import java.io.IOException;

public class HTTPServer {

	
	private HTTPServerObserver view;
	
	private Port port;
	private AcceptThread acceptThread;

	public HTTPServer(Port port, File sharedDirectory, HTTPServerObserver view) {
		this.view = view;
		view.serverConstructed();
		this.port = port;
	}

	public void start() throws IOException {
		
		acceptThread = new AcceptThread(port, view);
		
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
