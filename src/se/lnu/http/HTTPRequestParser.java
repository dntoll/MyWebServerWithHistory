package se.lnu.http;

import se.lnu.http.exceptions.MalformedRequestException;

public class HTTPRequestParser {

	public static HTTPRequest parseRequest(String requestString) throws MalformedRequestException {
		
		String[] parts = requestString.split("\r\n");
		if (parts.length < 1) {
			throw new MalformedRequestException(requestString);
		}
		
		String[] requestType = parts[0].split(" ");
		if (requestType.length != 3) {
			throw new MalformedRequestException(requestString);
		}
		
		HTTPRequest.Method method = HTTPRequest.Method.fromString(requestType[0]);
		
		
		return new HTTPRequest(method, requestType[1]);
	}


}
