package com.qq.client.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.qq.client.tools.ManageQqChat;
import com.qq.common.Message;

import java.awt.*;
import java.awt.event.*;

public class QqFriendList extends JFrame implements ActionListener, MouseListener{

	// Global
	CardLayout cl;
	
	// Friends
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jsp1;
	
	// Strangers
	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jsp2;
	
	// Owner
	String owner;
	
	// Friend list label
	JLabel []jbls;
	
	
	public static void main(String[] args) {

		//QqFriendList qfl = new QqFriendList("1");
	}
	
	public QqFriendList(String ownerId){
		
		owner = ownerId;
		
		// 1. Friend Outer
		jphy1 = new JPanel(new BorderLayout());
		
		// Friends list
		jphy_jb1 = new JButton("Friends");
		jphy_jb1.setForeground(Color.white);
		jphy_jb1.setBackground(new Color(0,164,219));
		jphy_jb1.setOpaque(true);
		
		jphy_jb2 = new JButton("Strangers");
		jphy_jb2.addActionListener(this);
		jphy_jb2.setForeground(Color.white);
		jphy_jb2.setBackground(new Color(60,202,250));
		jphy_jb2.setOpaque(true);
		
		jphy_jb3 = new JButton("BlackList");
		jphy_jb3.setForeground(Color.white);
		jphy_jb3.setBackground(new Color(60,202,250));
		jphy_jb3.setOpaque(true);
		
		// Middle
		jphy2 = new JPanel(new GridLayout(50, 1, 0, 0));
		jbls = new JLabel[50];		
		for(int i = 0; i < jbls.length; i++){
			jbls[i] = new JLabel(i+1+"", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			
			// Default owner online
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownerId)){
				jbls[i].setEnabled(true);
			}
			
			jbls[i].setBackground(Color.white);
			jbls[i].setOpaque(true);
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
			
			// Online Users
			
			
		}
		jsp1 = new JScrollPane(jphy2);

		// Bottom
		jphy3 = new JPanel(new GridLayout(2,1));
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		jphy1.add(jphy_jb1, "North");
		jphy1.add(jsp1, "Center");
		jphy1.add(jphy3, "South");
		
		
		
		// 2. Stranger Outer
		jpmsr1 = new JPanel(new BorderLayout());
		
		// Friends list
		jpmsr_jb1 = new JButton("Friends");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb1.setForeground(Color.white);
		jpmsr_jb1.setBackground(new Color(60,202,250));
		jpmsr_jb1.setOpaque(true);
		
		jpmsr_jb2 = new JButton("Strangers");
		jpmsr_jb2.setForeground(Color.white);
		jpmsr_jb2.setBackground(new Color(0,164,219));
		jpmsr_jb2.setOpaque(true);
		
		jpmsr_jb3 = new JButton("BlackList");
		jpmsr_jb3.setForeground(Color.white);
		jpmsr_jb3.setBackground(new Color(60,202,250));
		jpmsr_jb3.setOpaque(true);
		
		// Middle
		jpmsr2 = new JPanel(new GridLayout(20, 1, 0, 0));
		JLabel []jbls2 = new JLabel[20];		
		for(int i = 0; i < jbls2.length; i++){
			jbls2[i] = new JLabel(i+1+"", new ImageIcon("image/gg.jpg"), JLabel.LEFT);
			jbls2[i].setBackground(Color.white);
			jbls2[i].setOpaque(true);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		jsp2 = new JScrollPane(jpmsr2);

		// Top
		jpmsr3 = new JPanel(new GridLayout(2,1));
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");
		
		
		
		// Layout
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		
		this.setTitle(ownerId);
		this.setIconImage((new ImageIcon("image/mm.jpg")).getImage());
		this.setSize(300, 600);
		this.setVisible(true);
	}
	
	
	// Update friends list online statue
	public void updateFriend(Message m){
		String con = m.getCon();
		String onLineFriend[] = con.split(" ");
		
		// Enable JLabel
		for(int i = 0; i < onLineFriend.length; i++){
			jbls[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}
	}
	

	public void actionPerformed(ActionEvent e) {
		
		// 1. Show Stranger
		if(e.getSource() == jphy_jb2){
			cl.show(this.getContentPane(), "2");
		}
		// 2. Show Friends
		else if(e.getSource() == jpmsr_jb1){
			cl.show(this.getContentPane(), "1");
		}
	}

	public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount() == 2){
			
			String friendNo = ((JLabel)e.getSource()).getText();
			// System.out.println("talk to " + friendNo);
			QqChat qc = new QqChat(owner, friendNo);
			
			// Manage QqChat
			ManageQqChat.addQqChat(owner + " " + friendNo, qc);
			
			//Thread t = new Thread(qc);
			//t.start();
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.blue);
	}

	public void mouseExited(MouseEvent e) {
		
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.black);
	}
}
