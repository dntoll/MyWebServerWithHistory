package se.lnu.http.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import se.lnu.http.HTTPReader;

public class HTTPGetProtocoll {
	

	String doGet(String hostName, String file,
			BufferedWriter outWriter, BufferedReader reader) throws IOException {
		outWriter.write("GET " + file + " HTTP/1.1\r\nHost: " + hostName + "\r\nConnection: close\r\n\r\n");
		outWriter.flush();
		
		HTTPReader httpReader = new HTTPReader(reader);
		String data = httpReader.readAll();
		data += httpReader.readAll();
		return data;
	}

	
}