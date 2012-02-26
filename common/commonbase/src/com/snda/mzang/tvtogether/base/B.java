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

	// 消息长度，四个字节一个int
	int MSG_INT_LEN = 4;
	// 客户端给服务器端发送的handler名字，27个字节长度的字符
	int MSG_HANDLER_NAME_LEN = 27;

	// 对服务器来说，msg header就是这两个的和，对于客户端来说，就是MSG_INT_LEN
	int MSG_CLIENT_HEADER_LEN = MSG_INT_LEN + MSG_HANDLER_NAME_LEN;

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