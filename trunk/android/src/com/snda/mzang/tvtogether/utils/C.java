package com.snda.mzang.tvtogether.utils;

import org.json.JSONObject;

import com.snda.mzang.tvtogether.base.B;
import com.snda.mzang.tvtogether.utils.comm.IContentConverter;
import com.snda.mzang.tvtogether.utils.comm.IServerComm;
import com.snda.mzang.tvtogether.utils.comm.JSONConverter;
import com.snda.mzang.tvtogether.utils.comm.ServerCommSocket;

public interface C extends B {

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
	IServerComm comm = new ServerCommSocket("10.0.2.2", 17171);

	IContentConverter<JSONObject> jsonc = JSONConverter.JSON;

	/**
	 * 文件相关
	 */
	String sdroot = "/sdcard/";
	String CHANNEL_RES_LOCAL_DIR = sdroot + CHANNEL_RES_DIR;

}