package com.snda.mzang.tvtogether.utils.comm;

import org.json.JSONObject;

public interface IServerComm {
	public <T> T sendMsg(JSONObject msg, IContentConverter<T> converter);

	public JSONObject sendMsg(JSONObject msg);

}
