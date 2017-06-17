package com.qq.server.view;

import javax.swing.*;

import com.qq.server.model.MyQqServer;

import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author roy
 *	1. Start Server
 *	2. Stop Server
 *	3. Manage users
 *
 */
public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1, jb2;
	
	public static void main(String[] args) {
		
		MyServerFrame msf = new MyServerFrame();
	}

	public MyServerFrame(){
		
		jp1 = new JPanel();
		
		jb1 = new JButton("Start Server");
		jb1.setForeground(Color.white);
		jb1.setBackground(new Color(0,177,192));
		jb1.setOpaque(true);
		jb1.setBorderPainted(false);
		jb1.addActionListener(this);
		
		jb2 = new JButton("Stop Server");
		jb2.setForeground(Color.white);
		jb2.setBackground(new Color(239,78,81));
		jb2.setOpaque(true);
		jb2.setBorderPainted(false);
		
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(600, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jb1){
			
			new MyQqServer();
		}
	}
}
