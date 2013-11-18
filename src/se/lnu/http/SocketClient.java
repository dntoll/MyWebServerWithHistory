package se.lnu.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
	private HTTPGetProtocoll client;
	private Socket socket;

	public SocketClient(Socket echoSocket, HTTPGetProtocoll client) {
		this.socket = echoSocket;
		this.client = client;
	}

	public String get(String hostName, int portNumber, String file) throws IOException {
		
		try {
			InetSocketAddress endpoint = new InetSocketAddress(hostName, portNumber);
			socket.connect(endpoint, 100);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String data = client.doGet(hostName, file, writer, reader);
			
			socket.close();
			
			return data;
		} catch (IOException e) {
			throw e;
		}		
		
	}

	
	
	public static void main(String[] args) throws IOException {
		Socket echoSocket = new Socket();
		HTTPGetProtocoll httpClient = new HTTPGetProtocoll();
		SocketClient client = new SocketClient(echoSocket, httpClient);
		String ret = client.get("194.47.172.159", 80, "/");
		
		System.out.println(ret);
		
	}
}
