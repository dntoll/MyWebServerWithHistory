package se.lnu.http.response;

import java.io.IOException;

import se.lnu.http.ClientSocket;

public abstract class HTTPResponse {

	public abstract void writeResponse(ClientSocket clientSocket) throws IOException;
	
	protected String writeContentLengthAndEndHeader(byte[] bs) {
		String ret = "";
		ret += ("Content-Type: text/html\r\n");
		ret += ("Content-Length: " + bs.length + "\r\n");
		ret += ("Connection: close\r\n");
		
		
		ret += ("\r\n");
		
		
		return ret;
	}
/*public HTTPResponse createResponse( HTTPRequest request) {
	
	
	/*String content = "";
	if (request.getProtocoll().equals("GET")) {
		if (request.getURL().equals("/")) {
			content = "<html><body><h1>200 OK</h1><img src='img.jpg'/></body></html>";
			out.write("HTTP/1.1 200 OK\r\n");	
		} else {
			content = "<html><body><h1>404 Not found</h1></body></html>";
			out.write("HTTP/1.1 404 Not Found\r\n");
		}
		System.out.println(request.getURL());
		
	} else {
	
		content = "<html><body><h1>404</h1></body></html>";
		out.write("HTTP/1.1 404 Not Found\r\n");
	}
	out.write("Content-Type: text/html\r\n");
	out.write("Content-Length: " + content.length() + "\r\n");
	out.write("Connection: close\r\n");
	
	
	out.write("\r\n");
	out.write(content);
	
	return ret;
}*/
}
