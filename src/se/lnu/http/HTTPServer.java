package se.lnu.http;

import java.io.File;

public class HTTPServer {

	
	private HTTPServerObserver view;
	private boolean serverIsRunning;

	public HTTPServer(Port port, File sharedDirectory, HTTPServerObserver view) {
		this.view = view;
		serverIsRunning = false;
		view.serverConstructed();
		
	}

	public void start() {
		serverIsRunning = true;
		view.serverStarted();
	}

	public void stop() throws NotStartedException {
		if (serverIsRunning == false)
			throw new NotStartedException();
		view.serverStopped();
	}

}
