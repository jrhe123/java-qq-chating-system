package com.qq.client.model;

import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.User;

public class QqClientConServer {

	public Socket s;
	
	public boolean sendLoginInfoToServer(Object o){
		
		boolean b = false;
		try {
			
			// 1. Setup socket
			s = new Socket("127.0.0.1", 9999);
			
			// 2. Client -> Server
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			// 3. Server -> Client
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			if(ms.getMesType().equals("1")){
				b = true;
				
				// Create each qq and server connection thread
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				
				// Manage thread
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return b;
	}

}
