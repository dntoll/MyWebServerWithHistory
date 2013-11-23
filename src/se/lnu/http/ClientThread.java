package se.lnu.http;

import java.io.IOException;
import se.lnu.http.exceptions.MalformedRequestException;
import se.lnu.http.response.HTTPResponse;


public class ClientThread extends Thread{
	
	
	private ClientSocket clientSocket;
	private ResponseFactory factory;

	public ClientThread(ClientSocket clientSocket,
						ResponseFactory factory) {
		this.clientSocket = clientSocket;
		
		this.factory = factory;
	}
	
	public void run() {
		 
		try {
			
			
			String requestString = clientSocket.getRequest();
			
			HTTPRequest request = HTTPRequestParser.parseRequest(requestString);
			HTTPResponse response = factory.getResponse(request);
			
			response.writeResponse(clientSocket);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MalformedRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("client ended");
	}

	

	

}
