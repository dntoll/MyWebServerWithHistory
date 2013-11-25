package se.lnu.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import se.lnu.http.response.HTTP200OKFileResponse;
import se.lnu.http.response.HTTP400BadRequest;
import se.lnu.http.response.HTTP403Forbidden;
import se.lnu.http.response.HTTP404FileNotFoundResponse;
import se.lnu.http.response.HTTP405MethodNotSupportedResponse;
import se.lnu.http.response.HTTPResponse;

public class ResponseFactory {
	
	private SharedFolder folder;

	public ResponseFactory(SharedFolder folder) {
		this.folder = folder;
	}

	public HTTPResponse getResponse(HTTPRequest request) throws IOException {
		HTTPRequest.Method method = request.getMethod(); 
		if (method == HTTPRequest.Method.GET) {
			try {
				File file = folder.getURL(request.getURL());
				return new HTTP200OKFileResponse(file);
			} catch (FileNotFoundException e) {
				return new HTTP404FileNotFoundResponse(request.getURL());
			} catch (SecurityException e) {
				return new HTTP403Forbidden(request.getURL());
			}
			
		} 
		
		return new HTTP405MethodNotSupportedResponse(request.getMethod());
	}

	public HTTP400BadRequest getBadResponse() {
		return new HTTP400BadRequest();
	}
	
	

}
