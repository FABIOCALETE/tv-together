package com.snda.mzang.tvtogether.server.handler.processor;

import org.json.JSONObject;

import com.snda.mzang.tvtogether.base.B;
import com.snda.mzang.tvtogether.base.JSONUtil;
import com.snda.mzang.tvtogether.server.protocol.CommPackageProcessor;
import com.snda.mzang.tvtogether.server.protocol.IMessageProcessor;
import com.snda.mzang.tvtogether.server.protocol.IValidationProcessor;
import com.snda.mzang.tvtogether.server.util.SC;

public class GetServerResource implements IMessageProcessor, IValidationProcessor {

	public String getProcessorName() {
		return B.getServerResource;
	}

	public byte[] handle(JSONObject msg) {
		String resourceName = JSONUtil.getString(msg, B.resPathOnServ);
		return CommPackageProcessor.cache.get(SC.resBase + resourceName);
	}
}