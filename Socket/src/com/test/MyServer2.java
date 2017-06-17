package com.test;

import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class MyServer2 extends JFrame implements ActionListener{

	JTextArea jta = null;
	JTextField jtf = null;
	JButton jb = null;
	
	JScrollPane jsp = null;
	JPanel jp1 = null;
	
	// Socket
	PrintWriter pw = null;
	
	public static void main(String[] args) {

		MyServer2 ms2 = new MyServer2();
	}
	
	public MyServer2(){
		
		jta = new JTextArea();
		jtf = new JTextField(10);
		jb = new JButton("send");
		jb.addActionListener(this);
		
		jsp = new JScrollPane(jta);
		
		jp1 = new JPanel();
		jp1.add(jtf);
		jp1.add(jb);
		
		this.add(jsp, "Center");
		this.add(jp1, "South");
		
		this.setTitle("Chat room: server");
		this.setSize(400, 300);
		this.setVisible(true);
		
		
		try {
			// 1. Server socket
			ServerSocket ss = new ServerSocket(9999);
			Socket s = ss.accept();
			
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			
			pw = new PrintWriter(s.getOutputStream(), true);
			
			
			while (true) {
				
				// 2. Message from client
				String info = br.readLine();
				// Client -> Server
				jta.append("client: " + info + "\r\n");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jb){
			
			// Server -> Client
			String info = jtf.getText();
			
			jta.append("server: " + info + "\r\n");
			
			pw.println(info);
			jtf.setText("");
		}
		
	}
}
