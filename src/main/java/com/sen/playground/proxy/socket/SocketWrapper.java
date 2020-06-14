package com.sen.playground.proxy.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketWrapper {
	private Socket socket;
	
	private InputStream inputStream;
	
	private OutputStream outputStream;
	
	private BufferedReader inputReader;
	
	private BufferedWriter outputWriter;
	
	
	public SocketWrapper(Socket socket) throws IOException {
		this.socket = socket;
		this.inputStream = socket.getInputStream();
		this.outputStream = socket.getOutputStream();
		
		this.inputReader = new BufferedReader(
			new InputStreamReader(inputStream, "UTF-8"));
		
		this.outputWriter = new BufferedWriter( 
				new OutputStreamWriter(outputStream, "UTF-8"));		
	}
	
	public String readLine() throws IOException {
		return inputReader.readLine();
	}
	
	public void writeLine(String line) throws IOException {
		outputWriter.write(line + '\n');
		outputWriter.flush();
	}
	
	public void close() {
		try {
			this.socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
