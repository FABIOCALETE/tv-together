package com.snda.mzang.tvtogether.server.handler.processor;

import org.json.JSONObject;

public interface IMessageHandler {

	String getHandlerName();

	byte[] handle(JSONObject data);

}