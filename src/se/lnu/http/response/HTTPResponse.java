package se.lnu.http.response;

import java.io.IOException;

import se.lnu.http.ClientSocket;

public abstract class HTTPResponse {

	public abstract void writeResponse(ClientSocket clientSocket) throws IOException;
	
	protected String writeContentLengthAndEndHeader(long length, ContentType type) {
		String ret = "";
		ret += ("Content-Type: " + type.toString() + "\r\n");
		ret += ("Content-Length: " + length + "\r\n");
		ret += ("Connection: close\r\n");
		
		
		ret += ("\r\n");
		
		
		return ret;
	}

}
