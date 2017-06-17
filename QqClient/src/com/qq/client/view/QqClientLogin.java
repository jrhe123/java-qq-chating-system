package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QqClientLogin extends JFrame implements ActionListener{

	// Top
	JLabel jbl1;
	
	// Middle
	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;
	JPanel center1, center2, center3, center4;
	
	// Bottom
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;
	
	
	public static void main(String[] args) {

		QqClientLogin qcl = new QqClientLogin();
	}
	
	public QqClientLogin(){
		
		// Top
		jbl1 = new JLabel(new ImageIcon("image/top.PNG"));
		
		// Middle
		jp2 = new JPanel(new GridLayout(3,3));
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jp2_jbl1 = new JLabel("ID:", JLabel.CENTER);
		jp2_jbl2 = new JLabel("Password:", JLabel.CENTER);
		jp2_jbl3 = new JLabel("Forget", JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4 = new JLabel("Reset Password", JLabel.CENTER);
		
		jp2_jb1 = new JButton("clear");
		jp2_jb1.setPreferredSize (new Dimension(80, 35));
		jp2_jb1.setForeground(Color.white);
		jp2_jb1.setBackground(new Color(0,164,219));
		jp2_jb1.setOpaque(true);
		jp2_jb1.setBorderPainted(false);
		center4 = new JPanel();
		center4.add(jp2_jb1, BorderLayout.CENTER);
		
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		
		jp2_jcb1 = new JCheckBox("Incognito");
		jp2_jcb2 = new JCheckBox("Remember password");
		
		center1 = new JPanel();
		center1.add(jp2_jcb1, BorderLayout.CENTER);
		center2 = new JPanel();
		center2.add(jp2_jcb2, BorderLayout.CENTER);
		center3 = new JPanel();
		center3.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
		center3.add(jp2_jbl4, BorderLayout.CENTER);
		
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(center4);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(center1);
		jp2.add(center2);
		jp2.add(center3);
		
		
		jtp = new JTabbedPane();
		jtp.add("QQ", jp2);
		jtp.add("Phone", jp3);
		jtp.add("Email", jp4);
		
		
		// Bottom
		jp1 = new JPanel();
		jp1_jb1 = new JButton("Sign In");
		jp1_jb1.addActionListener(this);
		jp1_jb1.setForeground(Color.white);
		jp1_jb1.setBackground(new Color(0,164,219));
		jp1_jb1.setOpaque(true);
		jp1_jb1.setBorderPainted(false);
		
		jp1_jb2 = new JButton("Cancel");
		jp1_jb2.setForeground(Color.white);
		jp1_jb2.setBackground(new Color(0,164,219));
		jp1_jb2.setOpaque(true);
		jp1_jb2.setBorderPainted(false);
		
		jp1_jb3 = new JButton("Help");
		jp1_jb3.setForeground(Color.white);
		jp1_jb3.setBackground(new Color(0,164,219));
		jp1_jb3.setOpaque(true);
		jp1_jb3.setBorderPainted(false);
		
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		
		// Layout
		this.add(jbl1, "North");
		this.add(jtp, "Center");
		this.add(jp1, "South");
		
		this.setSize(500, 360);
		this.setIconImage((new ImageIcon("image/mm.jpg")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jp1_jb1){
			
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			QqClientUser qcu = new QqClientUser();
			if(qcu.checkUser(u)){
				
				try {
					// Show friends list
					QqFriendList qfl = new QqFriendList(u.getUserId());
					ManageQqFriendList.addManageQqFriendList(u.getUserId(), qfl);
					
					// Update online friends
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());					
					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "ID or password not correct!");
			}
		}
	}
}
