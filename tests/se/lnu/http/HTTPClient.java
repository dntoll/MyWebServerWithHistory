package se.lnu.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HTTPClient {
	private int contentlength = 0;

	public String HTTPGet(String hostName, int portNumber, String file) {
		Socket echoSocket;
		try {
			echoSocket = new Socket(hostName, portNumber);
			
			
			OutputStreamWriter outWriter = new OutputStreamWriter(echoSocket.getOutputStream());
			
			outWriter.write("GET " + file + " HTTP/1.1\r\nHost: " + hostName + "\r\n\r\n");
			outWriter.flush();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
			StringBuilder data = new StringBuilder();
			
			readHeader(reader, data);
			readBody(reader, data);
			
			echoSocket.close();
			
			return data.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return "Error";
		
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
		data.append("\n");
		
		//read header lines
		
		while (true) {
			String lineRead = reader.readLine();
			data.append(lineRead);
			data.append("\n");
			if (lineRead.equals("\r\n") || lineRead.equals("")) {
				break;
			}
			if (lineRead.startsWith("Content-Length:")) {
				String number = lineRead.substring(16);
				contentlength  = Integer.parseInt(number); 
			}
			
		}
	}
	
	public static void main(String[] args) {
		HTTPClient client = new HTTPClient();
		String ret = client.HTTPGet("194.47.172.159", 80, "/");
		
		System.out.println(ret);
		
	}
}
