package com.snda.mzang.tvtogether.utils.comm;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.exceptions.InvalidatedClientDataException;
import com.snda.mzang.tvtogether.exceptions.InvalidatedServerDataException;
import com.snda.mzang.tvtogether.utils.UserSession;

public class ServerCommMock implements IServerComm {

	private Map<String, MockupHandler> handlers = new HashMap<String, MockupHandler>();

	public ServerCommMock() {
		LoginHandlerMockup handler = new LoginHandlerMockup();
		handlers.put(handler.getHandlerName(), handler);
	}

	public JSONObject sendMsg(JSONObject msg) {
		if (msg == null) {
			throw new InvalidatedClientDataException("Message is null");
		}
		if (msg.has("handler") == false) {
			throw new InvalidatedClientDataException("Message has no handler");
		}
		try {
			msg.put("userName", UserSession.getUserName());
			msg.put("password", UserSession.getPassword());
		} catch (JSONException e1) {
			throw new InvalidatedClientDataException();
		}

		try {
			String handlerName = (String) msg.get("handler");
			MockupHandler handler = handlers.get(handlerName);
			if (handler == null) {
				throw new InvalidatedServerDataException("No handler found for name \"" + handlerName + "\"");
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
			ret.put("result", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}

}