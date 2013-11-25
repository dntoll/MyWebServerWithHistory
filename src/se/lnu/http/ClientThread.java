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
			HTTPResponse response;
			try {
				HTTPRequest request = HTTPRequestParser.parseRequest(requestString);
				response = factory.getResponse(request);
			} catch (MalformedRequestException e) {
				response = factory.getBadResponse();
			}
			
			
			response.writeResponse(clientSocket);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
