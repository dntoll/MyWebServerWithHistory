package se.lnu.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class HTTPGetProtocoll {
	private int contentlength;

	String doGet(String hostName, String file,
			BufferedWriter outWriter, BufferedReader reader) throws IOException {
		outWriter.write("GET " + file + " HTTP/1.1\r\nHost: " + hostName + "\r\n\r\n");
		outWriter.flush();
		StringBuilder data = new StringBuilder();
		readHeader(reader, data);
		readBody(reader, data);
		return data.toString();
	}

	private void readBody(BufferedReader reader, StringBuilder data) throws IOException {
		for (int i = 0; i < contentlength; i++) {
			data.append((char)reader.read());
		}
	}

	private void readHeader(BufferedReader reader, StringBuilder data)
			throws IOException {
		
		String statusLine = reader.readLine();
		data.append(statusLine);
		data.append("\r\n");
		
		//read header lines
		
		while (true) {
			String lineRead = reader.readLine();
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
	}
}