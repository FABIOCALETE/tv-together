package com.snda.mzang.tvtogether.server.comm;

import org.json.JSONObject;

public class CommPackage {

	public String type;

	public JSONObject data;

	@Override
	public String toString() {
		return "CommPackage [type=" + type + ", data=" + data + "]";
	}

}
