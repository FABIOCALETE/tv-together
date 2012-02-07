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

	/**
	 * 数据库相关
	 */
	String DB_NAME = "TVTogetherDB";

	String TB_USER = "UserInfo";
}
