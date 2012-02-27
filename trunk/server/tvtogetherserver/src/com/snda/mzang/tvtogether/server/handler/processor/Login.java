package com.snda.mzang.tvtogether.server.handler.processor;

import org.json.JSONException;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.base.B;
import com.snda.mzang.tvtogether.server.IMessageProcessor;
import com.snda.mzang.tvtogether.server.IValidationProcessor;

public class Login implements IMessageProcessor, IValidationProcessor {

	public String getProcessorName() {
		return B.login;
	}

	public byte[] handle(JSONObject msg) {
		JSONObject ret = new JSONObject();
		try {
			ret.put(B.result, B.success);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret.toString().getBytes();
	}

}