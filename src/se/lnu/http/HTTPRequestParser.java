package se.lnu.http;

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
		
		
		return new HTTPRequest(requestType[0], requestType[1]);
	}


}
