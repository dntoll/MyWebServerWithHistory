package se.lnu.http.response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import se.lnu.http.ClientSocket;

public class HTMLFileResponse extends HTTPResponse {

	private File file;

	public HTMLFileResponse(File file) {
		this.file = file;
	}

	@Override
	public void writeResponse(ClientSocket clientSocket) throws IOException {
		//String content = "<html><body><h1>200 OK</h1><img src='img.jpg'/></body></html>";
		
		byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		String response = ("HTTP/1.1 200 OK\r\n");
		response += writeContentLengthAndEndHeader(bytes);
		clientSocket.writeHeader(response);
		
		
		clientSocket.writeBody(bytes);
			
		 
	}

}
