package com.snda.mzang.tvtogether.utils.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.exceptions.CommunicationException;
import com.snda.mzang.tvtogether.exceptions.InvalidatedClientDataException;
import com.snda.mzang.tvtogether.exceptions.InvalidatedServerDataException;
import com.snda.mzang.tvtogether.utils.UserSession;

public class ServerCommSocket implements IServerComm {

	private InetSocketAddress address;

	public ServerCommSocket(String serviceIp, int serverPort) {
		address = InetSocketAddress.createUnresolved(serviceIp, serverPort);
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
		Socket socket = new Socket();
		try {
			socket.connect(address);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.write(msg.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuilder content = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
			return new JSONObject(content.toString());
		} catch (IOException e) {
			throw new CommunicationException(e);
		} catch (JSONException e) {
			throw new InvalidatedServerDataException(e);
		}

	}
}
