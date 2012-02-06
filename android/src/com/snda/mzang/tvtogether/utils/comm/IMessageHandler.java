package com.snda.mzang.tvtogether.utils.comm;

import org.json.JSONObject;

public interface IMessageHandler {

	String getHandlerName();

	void handle(JSONObject data);

}
