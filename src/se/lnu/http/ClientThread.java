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
			
			HTTPReader httpReader = new HTTPReader(in);
			String requestString = httpReader.readHeader();
			requestString += httpReader.readBody();
			
			HTTPRequest request = HTTPRequestParser.parseRequest(requestString);
			
			
			String content = "";
			if (request.getProtocoll().equals("GET")) {
				if (request.getURL().equals("/")) {
					content = "<html><body><h1>200 OK</h1><img src='img.jpg'/></body></html>";
					out.write("HTTP/1.1 200 OK\r\n");	
				} else {
					content = "<html><body><h1>404 Not found</h1></body></html>";
					out.write("HTTP/1.1 404 NotFound\r\n");
				}
				System.out.println(request.getURL());
				
			} else {
			
				content = "<html><body><h1>404</h1></body></html>";
				out.write("HTTP/1.1 404 Not Found\r\n");
			}
			out.write("Content-Type: text/html\r\n");
			out.write("Content-Length: " + content.length() + "\r\n");
			out.write("Connection: close\r\n");
			
			
			out.write("\r\n");
			out.write(content);
			out.flush();
			
			this.clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MalformedRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("client ended");
	}

	

}
