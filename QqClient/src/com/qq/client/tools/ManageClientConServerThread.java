package com.qq.client.tools;

import java.util.*;

public class ManageClientConServerThread {

	private static HashMap hm = new HashMap<String, ClientConServerThread>();
	
	public static void addClientConServerThread(String uId, ClientConServerThread c){
		hm.put(uId, c);
	}
	
	public static ClientConServerThread getClientConServerThread(String uId){
		return (ClientConServerThread)hm.get(uId);
	}
}
