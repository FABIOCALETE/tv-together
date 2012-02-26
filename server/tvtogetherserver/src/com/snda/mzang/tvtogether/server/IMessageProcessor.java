package com.snda.mzang.tvtogether.server;

import org.json.JSONObject;

public interface IMessageProcessor {

	String getProcessorName();

	byte[] handle(JSONObject data);

}