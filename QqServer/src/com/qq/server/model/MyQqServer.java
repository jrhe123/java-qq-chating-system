package com.qq.server.model;
/**
 * 
 * @author roy
 *
 *	1. Server socket
 *	2. Socket
 */

import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.common.Message;
import com.qq.common.User;

public class MyQqServer {

	public MyQqServer(){
		
		try {
			
			System.out.println("Server listening on port 9999..");
			// 1. Setup server socket
			ServerSocket ss = new ServerSocket(9999);
			
			while(true){
				
				Socket s = ss.accept();
				
				// 2. Client -> Server
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				
				// 3. Server -> Client
				Message ms = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123456")){
					ms.setMesType("1");
					oos.writeObject(ms);
					
					// Create chat thread for login user
					SerConClientThread scct = new SerConClientThread(s);
					scct.start();
					
					// HashMap (uid, thread)
					ManageClientThread.addClientThread(u.getUserId(), scct);
					
					
					// Notify online users
					scct.notifyOther(u.getUserId());
					
				}else{
					ms.setMesType("2");
					oos.writeObject(ms);
					s.close();
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
		}
	}
}
