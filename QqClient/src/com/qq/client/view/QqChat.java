package com.qq.client.view;

import javax.swing.*;

import com.qq.client.model.QqClientConServer;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class QqChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	
	String ownerId;
	String friendId;
	
	public static void main(String[] args) {

		//QqChat qc = new QqChat("123","123");
	}
	
	public QqChat(String owner, String friend){
		
		ownerId = owner;
		friendId = friend;
		
		jta = new JTextArea();
		
		jtf = new JTextField(20);
		jtf.setPreferredSize(new Dimension(150, 28));
		
		jb = new JButton("Send");
		jb.setForeground(Color.white);
		jb.setBackground(new Color(0,164,219));
		jb.setOpaque(true);
		jb.setBorderPainted(false);
		jb.addActionListener(this);
		
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta, "Center");
		this.add(jp, "South");
		this.setSize(400,300);
		this.setTitle(owner + " chating with " + friend + " ..");
		this.setIconImage((new ImageIcon("image/mm.jpg")).getImage());
		this.setVisible(true);
	}
	
	
	public void showMessage(Message ms){
		
		String info = ms.getSender() + " chat to " + ms.getGetter() + " : " + ms.getCon() + "\r\n";
		jta.append(info);
	}
	

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jb){
			
			Message ms = new Message();
			ms.setMesType(MessageType.message_comm);
			ms.setSender(ownerId);
			ms.setGetter(friendId);
			ms.setCon(jtf.getText());
			ms.setSendTime(new Date().toString());
			
			try {
				
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(ms);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
//	public void run() {
//		
//		while(true){
//			try {
//				
//				ObjectInputStream ois = new ObjectInputStream(QqClientConServer.s.getInputStream());
//				Message ms = (Message)ois.readObject();
//				
//				// Append to text area
//				String info = ms.getSender() + " chat to " + ms.getGetter() + " : " + ms.getCon() + "\r\n";
//				jta.append(info);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
}
