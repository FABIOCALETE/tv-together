package com.snda.mzang.tvtogether.server.comm;

import org.json.JSONObject;

public interface IMessageProcessor {

	String getProcessorName();

	byte[] handle(JSONObject data);

}