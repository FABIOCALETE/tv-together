package com.snda.mzang.tvtogether.utils;

public class UserSession {

	public static String userName;

	public static String password;

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		UserSession.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		UserSession.password = password;
	}

}
