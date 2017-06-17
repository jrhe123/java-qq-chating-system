package com.qq.common;

public interface MessageType {

	String message_succeed = "1"; // Login in succeed
	String message_login_fail = "2"; // Login in failed
	String message_comm = "3"; // Common message
	String message_get_onLineFriend = "4"; // Request online users
	String message_ret_onLineFriend = "5"; // Return online users
}
