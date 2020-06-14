package com.sen.playground.proxy.socket;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		SocketWrapper socket = new SocketWrapper(new Socket("localhost", 8888));
		try {
			System.out.println("Server connection established!");
			String sendMsg = scanner.nextLine();
			socket.writeLine(sendMsg);
			String recievedMsg = socket.readLine();
			while(!"close".equals(recievedMsg)) {
				System.out.println("===Server returned msg=== :" + recievedMsg);
				sendMsg = scanner.nextLine();
				socket.writeLine(sendMsg);
				recievedMsg = socket.readLine();
			}
			
			System.out.println("Client exited.");
		}finally {
			if(socket!=null)
				socket.close();
		}
	}
}
