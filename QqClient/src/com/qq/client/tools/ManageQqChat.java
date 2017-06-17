package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.*;

public class ManageQqChat {

	private static HashMap hm = new HashMap<String, QqChat>();
	
	public static void addQqChat(String loginIdAnFriendId, QqChat qqChat){
		hm.put(loginIdAnFriendId, qqChat);
	}
	
	public static QqChat getQqChat(String loginIdAnFriendId){
		return (QqChat)hm.get(loginIdAnFriendId);
	}
}
