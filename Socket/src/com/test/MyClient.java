package com.test;

import java.net.*;
import java.io.*;

public class MyClient {

	public static void main(String[] args) {
		
		MyClient mc = new MyClient();
	}

	public MyClient(){
		
		try {
			
			// 1. Connect Server
			Socket s = new Socket("127.0.0.1",9999);
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			
			// 2. Client message from i/o
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			// 3. Receive from server
			InputStreamReader isr2 = new InputStreamReader(s.getInputStream());
			BufferedReader br2 = new BufferedReader(isr2);
			
			while(true){
				
				System.out.println("Please type your message here:");
				String info = br.readLine();
				
				// Client -> Server
				pw.println(info);
				
				
				// Close socket
				if(info.equals("bye")){
					System.out.println("client end");
					s.close();
					break;
				}
				
				
				// Server -> Client
				String res = br2.readLine();
				System.out.println("Message from server: "+ res);
								
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
