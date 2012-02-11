package com.snda.mzang.tvtogether.utils;

import com.snda.mzang.tvtogether.utils.comm.IServerComm;
import com.snda.mzang.tvtogether.utils.comm.ServerCommMock;

public interface C {

	/**
	 * UI相关
	 */
	String dummyPassword = "000000000";

	/**
	 * log相关
	 * 
	 */
	String TAG = "TVTogether";

	/**
	 * 通讯相关
	 * 
	 */
	IServerComm comm = new ServerCommMock();

	String result = "result";
	String success = "success";
	String fail = "fail";
	String handler = "handler";
	String username = "username";
	String password = "password";
	String keepLogin = "keepLogin";
	String regNewUser = "regNewUser";

	String channels = "channels";

	/**
	 * 数据库相关
	 */
	String DB_NAME = "TVTogetherDB";

	String TB_USER = "UserInfo";

	String col_username = "username";
	String col_password = "password";

	/**
	 * 文件相关
	 */
	String CHANNEL_RES_DIR = "/sdcard/tvtogether/channelres/";
}
