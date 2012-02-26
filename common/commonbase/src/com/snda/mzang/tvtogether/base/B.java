package com.snda.mzang.tvtogether.base;

public interface B {

	/**
	 * 通讯相关
	 * 
	 */
	String result = "result";
	String success = "success";
	String fail = "fail";
	String handler = "handler";
	String username = "username";
	String password = "password";
	String keepLogin = "keepLogin";
	String regNewUser = "regNewUser";

	int lenStr = 4;
	int lenType = 27;

	int lenHeader = lenStr + lenType;

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
	String CHANNEL_RES_DIR = "tvtogether/channelres/";

}
