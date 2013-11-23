package se.lnu.http.response;

import java.io.IOException;

import se.lnu.http.ClientSocket;

public class HTTP404FileNotFoundResponse extends HTTPResponse {

	public HTTP404FileNotFoundResponse(String url) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void writeResponse(ClientSocket clientSocket) throws IOException {
		String content = "<html><body><h1>404 Not found</h1></body></html>";
		
		
		String response = ("HTTP/1.1 404 Not Found\r\n");
		response += endWithContent(content);
		clientSocket.writeResponse(response);
			
		 
	}

}
