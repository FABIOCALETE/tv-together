package com.snda.mzang.tvtogether.utils;

import org.json.JSONObject;

import com.snda.mzang.tvtogether.utils.comm.IContentConverter;
import com.snda.mzang.tvtogether.utils.comm.IServerComm;
import com.snda.mzang.tvtogether.utils.comm.JSONConverter;
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

	// 获取频道列表时，channel节点在json中的名字
	String channels = "channels";

	// 获得server resource的时，资源的路径名
	String resPathOnServ = "resPathOnServ";

	/**
	 * server hanlder的名字
	 */
	String loginHandler = "loginHandler";
	String getChannelListHandler = "getChannelListHandler";
	String getServerResource = "getServerResource";

	IContentConverter<JSONObject> jsonc = JSONConverter.JSON;

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
	String sdroot = "/sdcard/";
	String CHANNEL_RES_DIR = "tvtogether/channelres/";
	String CHANNEL_RES_LOCAL_DIR = sdroot + CHANNEL_RES_DIR;

}
