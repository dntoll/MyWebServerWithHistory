package se.lnu.http;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import se.lnu.http.response.HTMLFileResponse;
import se.lnu.http.response.HTTP404FileNotFoundResponse;
import se.lnu.http.response.HTTP405MethodNotSupportedResponse;
import se.lnu.http.response.HTTPResponse;

public class ResponseFactory {
	
	private SharedFolder folder;

	public ResponseFactory(SharedFolder folder) {
		this.folder = folder;
	}

	public HTTPResponse getResponse(HTTPRequest request) {
		HTTPRequest.Method method = request.getMethod(); 
		if (method == HTTPRequest.Method.GET) {
			try {
				File file = folder.getURL(request.getURL());
				return new HTMLFileResponse(file);
			} catch (FileNotFoundException e) {
				return new HTTP404FileNotFoundResponse(request.getURL());
			}
			
		} 
		
		return new HTTP405MethodNotSupportedResponse(request.getMethod());
	}
	
	

}
