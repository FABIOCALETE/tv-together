package com.snda.mzang.tvtogether.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {

	public static String getString(JSONObject data, String key) {
		if (data == null) {
			return null;
		}
		try {
			return (String) data.get(key);
		} catch (JSONException e) {
			return null;
		}
	}

}
