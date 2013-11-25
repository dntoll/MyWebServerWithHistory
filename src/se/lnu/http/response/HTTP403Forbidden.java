package se.lnu.http.response;

import java.io.IOException;

import se.lnu.http.ClientSocket;

public class HTTP403Forbidden extends HTTPResponse {

	private String url;

	public HTTP403Forbidden(String url) {
		this.url = url;
	}

	@Override
	public void writeResponse(ClientSocket clientSocket) throws IOException {
		String content = "<html><body><h1>403 Forbidden</h1></body></html>";
		
		
		String response = ("HTTP/1.1 403 Forbidden\r\n");
		response += writeContentLengthAndEndHeader(content.getBytes().length, ContentType.texthtml);
		clientSocket.writeHeader(response);
		clientSocket.writeBody(content.getBytes());
			
		 
	}

}
