package se.lnu.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class AcceptThread extends Thread{

	volatile ServerSocket socket;
	volatile private HTTPServerObserver observer;
	public AcceptThread(Port port, HTTPServerObserver observer) throws IOException {
		socket = new ServerSocket(port.getPort()); 
		this.observer = observer;
	}
	
	public void stopme() throws IOException {
		socket.close();
	}
	
	public void run() {
        
        	
        	while (true) {
        		try {
        			observer.waitForClient();
        			Socket clientSocket = socket.accept();
        			ClientThread client = new ClientThread(clientSocket);
    				client.start();
    				observer.startedClient();
        		} catch (SocketException e) {
        			break;
                } catch (IOException e) {
        			e.printStackTrace();
                }
				
				
        	}
        	
        	observer.closedAccept();
        	
		
    } 
	
}
