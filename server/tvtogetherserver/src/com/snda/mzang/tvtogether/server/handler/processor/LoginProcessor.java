package com.snda.mzang.tvtogether.server.handler.processor;

import org.json.JSONException;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.base.B;

public class LoginProcessor implements IMessageProcessor, IValidationProcessor {

	public String getProcessorName() {
		return B.loginHandler;
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