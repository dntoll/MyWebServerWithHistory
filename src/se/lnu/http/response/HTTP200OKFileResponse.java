package se.lnu.http.response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import se.lnu.http.ClientSocket;

public class HTTP200OKFileResponse extends HTTPResponse {

	private File file;

	public HTTP200OKFileResponse(File file) {
		this.file = file;
	}

	@Override
	public void writeResponse(ClientSocket clientSocket) throws IOException {
		String fileName = file.getName();
		String parts[] = fileName.split("\\.");
		
		
		ContentType type = ContentType.getFromFileEnding(parts[parts.length-1]);
		
		String response = ("HTTP/1.1 200 OK\r\n");
		
		response += writeContentLengthAndEndHeader(file.length(), type);
		clientSocket.writeHeader(response);
		
		byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		clientSocket.writeBody(bytes);
			
		 
	}

}
