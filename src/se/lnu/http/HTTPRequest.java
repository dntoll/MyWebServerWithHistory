package se.lnu.http;

public class HTTPRequest {
	
	private String url;
	private String requestType;

	public HTTPRequest(String requestType, String url) {
		this.url = url;
		this.requestType = requestType;
	}

	public String getURL() {
		
		return this.url;
	}

	public String getProtocoll() {
		return requestType;
	}

}
