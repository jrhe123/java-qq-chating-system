package com.qq.client.tools;

import java.net.*;
import java.io.*;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class ClientConServerThread extends Thread{

	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConServerThread(Socket s){
		this.s = s;
	}
	
	public void run(){
		
		while(true){
			
			try {
				
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message)ois.readObject();
				
				
				// Common message
				if(ms.getMesType().equals(MessageType.message_comm)){
					
					System.out.println("Message from server: " + ms.getSender() + " to " + ms.getGetter() + ", " + ms.getCon());
					
					// Message from server, append the QqChat
					QqChat qc = ManageQqChat.getQqChat(ms.getGetter() + " " + ms.getSender());
					qc.showMessage(ms);
				}
				// Check online friends
				else if(ms.getMesType().equals(MessageType.message_ret_onLineFriend)){
										
					System.out.println("Update friend list: " + ms.getGetter() + " : " + ms.getCon());
					
					// Update friends list
					String getter = ms.getGetter();
					QqFriendList qfl = ManageQqFriendList.getQqFriendList(getter);
					if(qfl != null){
						qfl.updateFriend(ms);
					}
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}