package com.test;

import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class MyClient2 extends JFrame implements ActionListener{

	JTextArea jta = null;
	JTextField jtf = null;
	JButton jb = null;
	
	JScrollPane jsp = null;
	JPanel jp1 = null;
	
	PrintWriter pw = null;
	
	public static void main(String[] args) {

		MyClient2 mc2 = new MyClient2();
	}
	
	public MyClient2(){
		
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
		
		this.setTitle("Chat room: client");
		this.setSize(400, 300);
		this.setVisible(true);
		
		try {
			
			Socket s = new Socket("127.0.0.1", 9999);
			
			// Message from server
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			pw = new PrintWriter(s.getOutputStream(), true);
			
			while(true){
				
				String res = br.readLine();
				jta.append("server: " + res + "\r\n");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jb){
			
			String info = jtf.getText();
			
			jta.append("client: " + info + "\r\n");
			
			pw.println(info);
			jtf.setText("");
		}
	}

}
