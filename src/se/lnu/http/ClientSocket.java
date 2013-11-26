package se.lnu.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
	
	private Socket sock;

	public ClientSocket(Socket sock) {
		this.sock = sock;
		
	}

	public String getRequest(int timeOutMilliseconds) throws IOException {
		
		sock.setSoTimeout(timeOutMilliseconds);
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		HTTPReader httpReader = new HTTPReader(in);
		String requestString = httpReader.readAll();
		return requestString;
	}

	public void writeHeader(String response) throws IOException {
		PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
		out.write(response.toString());
		out.flush();
	}

	public void close() throws IOException {
		sock.close();
		
	}

	public void writeBody(byte[] bytes) throws IOException {
		sock.getOutputStream().write(bytes);
	}

}
