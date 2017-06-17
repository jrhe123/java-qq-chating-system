package com.qq.server.model;

import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

import com.qq.common.Message;
import com.qq.common.MessageType;

public class SerConClientThread extends Thread{

	Socket s;
	
	public SerConClientThread(Socket s){
		this.s = s;
	}
	
	// Notify online users
	public void notifyOther(String owner){
		
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext()){
			
			String onLineUserId = it.next().toString();
			Message m = new Message();
			m.setCon(owner);
			m.setMesType(MessageType.message_ret_onLineFriend);
			try {
				
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
		
		while(true){

			try {
				// Client 1 -> Server
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message) ois.readObject();
				
				// Check message Type
				// Common message
				if(ms.getMesType().equals(MessageType.message_comm)){
					System.out.println(ms.getSender() + " chat to " + ms.getGetter() + ": " + ms.getCon());
					// Server -> Client 2
					SerConClientThread sc = ManageClientThread.getClientThread(ms.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(ms);
				}
				// Online friends list
				else if(ms.getMesType().equals(MessageType.message_get_onLineFriend)){
					
					System.out.println("request " + ms.getSender() + " friend list");
					String res = ManageClientThread.getAllOnlineUserid();
					Message m = new Message();
					m.setMesType(MessageType.message_ret_onLineFriend);
					m.setCon(res);
					m.setGetter(ms.getSender());
					// Server -> Client
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m);
				}
				
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
