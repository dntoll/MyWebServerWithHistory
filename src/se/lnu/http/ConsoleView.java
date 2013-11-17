package se.lnu.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements HTTPServerObserver {
	static final String HELP_TEXT = "Enter a valid port 1-65535 and a optional URL";
	static final String SERVER_CONSTRUCTED = "HTTP Server object constructed";
	static final String SERVER_STARTED = "HTTP Server started";
	static final String SERVER_STOPPED = "HTTP Server stopped";
	
	private String[] args;
	
	
	
	public ConsoleView(String[] args) throws WrongNumberOfArgumentsException {
		if (args.length > 2 || args.length < 1) {
			throw new WrongNumberOfArgumentsException();
		}
		
		this.args = args;
		
	}

	public Port getPort() throws InvalidPortException {
		int port;
		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			throw new InvalidPortException("not an integer argument");
		}
		Port p = new Port(port);
		
		return p;
	}

	
	public void showhelp() {
		System.out.print(HELP_TEXT);
		
	}
	
	

	public File getDirectory() throws NotADirectoryException {
		File directory;
		try {
			directory = new File(args[1]);//may throw nullpointerexception
			
			
			if (directory.exists() == false) {
				throw new NotADirectoryException("directory does not exists");
			}
			if (directory.isDirectory() == false) {
				throw new NotADirectoryException("url is not a directory");
			}
			
		} catch (Exception e) {
			throw new NotADirectoryException(e);
		}
		
		
		return directory;
	}

	public boolean doStop() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		if (line.equals("stop")) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void serverConstructed() {
		System.out.print(SERVER_CONSTRUCTED);
		
	}

	@Override
	public void serverStarted() {
		System.out.print(SERVER_STARTED);
		
	}

	@Override
	public void serverStopped() {
		System.out.print(SERVER_STOPPED);
		
	}

	

	
}
