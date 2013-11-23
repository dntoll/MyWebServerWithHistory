package se.lnu.http;

import java.net.Socket;

public class ClientFactory {
	
	private SharedFolder folder;

	ClientFactory(SharedFolder folder) {
		this.folder = folder;
	}

	public ClientThread createClient(Socket clientSocket ) {
		
		return new ClientThread(new ClientSocket(clientSocket), new ResponseFactory(folder));
	}

}
