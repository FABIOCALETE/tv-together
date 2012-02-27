package com.snda.mzang.tvtogether.server.handler.processor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.base.B;
import com.snda.mzang.tvtogether.server.CommPackageProcessor;
import com.snda.mzang.tvtogether.server.IMessageProcessor;

public class GetChannelList implements IMessageProcessor {

	public String getProcessorName() {
		return B.getChannelList;
	}

	public byte[] handle(JSONObject msg) {
		JSONObject ret = new JSONObject();
		try {

			String[] channelNames = CommPackageProcessor.loadChannelInfos();

			ret.put(B.result, B.success);
			JSONArray channels = new JSONArray();
			for (String channelName : channelNames) {
				channels.put(channelName);
			}
			ret.put(B.channels, channels);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret.toString().getBytes();
	}
}