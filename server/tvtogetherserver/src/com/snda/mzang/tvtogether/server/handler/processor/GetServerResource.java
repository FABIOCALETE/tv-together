package com.snda.mzang.tvtogether.server.handler.processor;

import org.json.JSONObject;

import com.snda.mzang.tvtogether.base.B;
import com.snda.mzang.tvtogether.base.JSONUtil;
import com.snda.mzang.tvtogether.server.comm.CommPackageProcessor;
import com.snda.mzang.tvtogether.server.comm.IMessageProcessor;
import com.snda.mzang.tvtogether.server.comm.IValidationProcessor;
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