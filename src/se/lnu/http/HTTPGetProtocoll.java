package se.lnu.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class HTTPGetProtocoll {
	

	String doGet(String hostName, String file,
			BufferedWriter outWriter, BufferedReader reader) throws IOException {
		outWriter.write("GET " + file + " HTTP/1.1\r\nHost: " + hostName + "\r\n\r\n");
		outWriter.flush();
		
		HTTPReader httpReader = new HTTPReader(reader);
		String data = httpReader.readHeader();
		data += httpReader.readBody();
		return data;
	}

	
}