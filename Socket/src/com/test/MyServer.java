package com.test;

import java.io.*;
import java.net.*;

public class MyServer {

	public static void main(String[] args) {

		MyServer ms = new MyServer();
	}
	
	public MyServer(){
		
		try {
			System.out.println("listen on 9999..");
			// 1. Setup server socket, listen on port 9999
			ServerSocket ss = new ServerSocket(9999);
			// 2. Wait 
			Socket s = ss.accept();
			
			// 3. Message from client
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			// 4. Send message to client
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			
			// 5. Server message from i/o
			InputStreamReader isr2 = new InputStreamReader(System.in);
			BufferedReader br2 = new BufferedReader(isr2);
			
			while(true){
				
				// Client -> Server
				String infoFromClient = br.readLine();
				System.out.println("from client: " + infoFromClient);
				
				
				// Close socket
				if(infoFromClient.equals("bye")){
					System.out.println("server end");
					s.close();
					break;
				}
				
				
				System.out.println("please response here: ");
				String response = br2.readLine();
				
				// Server -> Client
				pw.println(response);
								
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
