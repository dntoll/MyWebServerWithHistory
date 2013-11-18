package se.lnu.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	
	
	private Socket clientSocket;

	public ClientThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public void run() {
		 
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String content = "<html><body><h1>404</h1></body></html>";
			
			out.write("HTTP/1.0 404 Not Found\r\n");
			out.write("Content-Type: text/html\r\n");
			out.write("Content-Length: " + content.length() + "\r\n");
			out.write("Connection: close\r\n");
			
			
			out.write("\r\n");
			out.write(content);
			out.flush();
			
			this.clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
