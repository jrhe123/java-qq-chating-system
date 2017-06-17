package com.qq.client.tools;

import java.util.*;
import java.io.*;
import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {

	private static HashMap hm = new HashMap<String, QqFriendList>();
	
	public static void addManageQqFriendList(String uId, QqFriendList qfl){
		hm.put(uId, qfl);
	}
	
	public static QqFriendList getQqFriendList(String uId){
		return (QqFriendList)hm.get(uId);
	}
}
