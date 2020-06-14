package com.sen.playground.proxy.socket;

import java.io.IOException;
import java.net.ServerSocket;
public class Server {
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888);
		System.out.println("port open 8888, waiting incoming data...");
		SocketWrapper socket = null;
		try {
			socket = new SocketWrapper(serverSocket.accept());
			String line = socket.readLine();
			while(!"bye".equals(line)) {
				System.out.println("Data from client: " + line);
				socket.writeLine("I received your data: " + line);
				line = socket.readLine();
			}
			socket.writeLine("close connection");
		}finally {
			if(socket!=null) {
				socket.close();
			}
		}
	}

}
