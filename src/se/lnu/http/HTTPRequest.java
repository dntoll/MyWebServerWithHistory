package se.lnu.http;

import se.lnu.http.exceptions.*;

public class HTTPRequest {
	//http://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol
	public enum Method {
	    GET, POST, HEAD, PUT, DELETE, TRACE, OPTIONS, CONNECT, PATCH;
	    
	    static Method fromString(String stringMethod) throws MalformedRequestException {
	    	for(Method m : Method.values()) {
	    		if (stringMethod.equals(m.name())) {
	    			return m;
	    		}
	    	}
	    	throw new MalformedRequestException("unknown method ["+stringMethod+"]");
	    	
	    }
	}
	
	private String url;
	private Method requestType;

	public HTTPRequest(Method requestType, String url) {
		this.url = url;
		this.requestType = requestType;
	}

	public String getURL() {
		
		return this.url;
	}

	public Method getMethod() {
		return requestType;
	}

}
