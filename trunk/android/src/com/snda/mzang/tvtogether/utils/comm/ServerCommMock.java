package com.snda.mzang.tvtogether.utils.comm;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.exceptions.InvalidatedClientDataException;
import com.snda.mzang.tvtogether.exceptions.InvalidatedServerDataException;
import com.snda.mzang.tvtogether.utils.C;
import com.snda.mzang.tvtogether.utils.UserSession;

public class ServerCommMock implements IServerComm {

	private Map<String, MockupHandler> handlers = new HashMap<String, MockupHandler>();

	public ServerCommMock() {
		MockupHandler handler = new LoginHandlerMockup();
		handlers.put(handler.getHandlerName(), handler);
		handler = new GetChannelListHandlerMockup();
		handlers.put(handler.getHandlerName(), handler);

	}

	public JSONObject sendMsg(JSONObject msg) {
		if (msg == null) {
			throw new InvalidatedClientDataException("Message is null");
		}
		if (msg.has(C.handler) == false) {
			throw new InvalidatedClientDataException("Message has no handler");
		}
		try {
			msg.put(C.username, UserSession.getUserName());
			msg.put(C.password, UserSession.getPassword());
		} catch (JSONException e1) {
			throw new InvalidatedClientDataException();
		}

		try {
			String handlerName = (String) msg.get(C.handler);
			MockupHandler handler = handlers.get(handlerName);
			if (handler == null) {
				throw new InvalidatedServerDataException("No handler found for name \"" + handlerName + "\"");
			}
			try {
				// mock waiting :)
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			return handler.handle(msg);
		} catch (JSONException e) {
			throw new InvalidatedClientDataException();
		}

	}

}

interface MockupHandler {
	String getHandlerName();

	JSONObject handle(JSONObject msg);
}

class LoginHandlerMockup implements MockupHandler {

	public String getHandlerName() {
		return "loginHandler";
	}

	public JSONObject handle(JSONObject msg) {
		JSONObject ret = new JSONObject();
		try {
			ret.put(C.result, C.success);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}

}

class GetChannelListHandlerMockup implements MockupHandler {

	public String getHandlerName() {
		return "getChannelListHandler";
	}

	public JSONObject handle(JSONObject msg) {
		JSONObject ret = new JSONObject();
		try {
			ret.put(C.result, C.success);
			JSONArray channels = new JSONArray();
			channels.put("山东电视台");
			channels.put("中央电视台");
			channels.put("OO电视台");
			channels.put("XX电视台");
			channels.put("BBC电视台");
			channels.put("VOA电视台");
			channels.put("CNN");
			channels.put("探索·发现频道电视台");
			channels.put("共匪是傻逼电视台");
			channels.put("CCAV电视台");
			channels.put("CC-AV电视台");
			channels.put("CC既AV电视台");
			channels.put("环球时报统一全世界电视台");
			channels.put("人民日报日人民电视台");
			ret.put(C.channels, channels);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}
}