package se.lnu.http;

import java.io.BufferedReader;
import java.io.IOException;

public class HTTPReader {
	private int contentlength = 0;
	
	BufferedReader reader;
	
	public HTTPReader(BufferedReader reader) {
		this.reader = reader;
	}

	public String readBody() throws IOException {
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < contentlength; i++) {
			data.append((char)reader.read());
		}
		
		return data.toString();
	}

	public String readHeader()
			throws IOException {
		StringBuilder data = new StringBuilder();
		String statusLine = reader.readLine();
		
		if (statusLine == null) { //eof http://docs.oracle.com/javase/1.4.2/docs/api/java/io/BufferedReader.html
			throw new IOException("eof");
		}
		data.append(statusLine);
		data.append("\r\n");
		
		//read header lines
		
		while (true) {
			String lineRead = reader.readLine();
			
			if (lineRead == null) { //eof http://docs.oracle.com/javase/1.4.2/docs/api/java/io/BufferedReader.html
				throw new IOException("eof");
			}
			
			data.append(lineRead);
			data.append("\r\n");
			if (lineRead == null || lineRead.equals("\r\n") || lineRead.equals("")) {
				break;
			}
			if (lineRead.startsWith("Content-Length:")) {
				String number = lineRead.substring(16);
				contentlength  = Integer.parseInt(number); 
			}
			
		}
		
		return data.toString();
	}
}