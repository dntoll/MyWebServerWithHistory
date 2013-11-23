package se.lnu.http.response;

import java.io.File;
import java.io.IOException;

import se.lnu.http.ClientSocket;

public class HTMLFileResponse extends HTTPResponse {

	public HTMLFileResponse(File file) {
		
	}

	@Override
	public void writeResponse(ClientSocket clientSocket) throws IOException {
		String content = "<html><body><h1>200 OK</h1><img src='img.jpg'/></body></html>";
		
		
		String response = ("HTTP/1.1 200 OK\r\n");
		response += endWithContent(content);
		clientSocket.writeResponse(response);
			
		 
	}

}
