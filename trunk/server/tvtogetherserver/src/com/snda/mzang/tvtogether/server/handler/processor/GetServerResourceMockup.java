package com.snda.mzang.tvtogether.server.handler.processor;

import org.json.JSONObject;

import com.snda.mzang.tvtogether.base.B;
import com.snda.mzang.tvtogether.base.JSONUtil;
import com.snda.mzang.tvtogether.server.CommPackageProcessor;
import com.snda.mzang.tvtogether.server.SC;

public class GetServerResourceMockup implements IMessageHandler, IValidationHandler {

	public String getHandlerName() {
		return B.getServerResource;
	}

	public byte[] handle(JSONObject msg) {
		String resourceName = JSONUtil.getString(msg, B.resPathOnServ);
		return CommPackageProcessor.cache.get(SC.resBase + resourceName);
	}
}