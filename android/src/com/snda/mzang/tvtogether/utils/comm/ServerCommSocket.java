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
import com.snda.mzang.tvtogether.utils.C;
import com.snda.mzang.tvtogether.utils.UserSession;

public class ServerCommSocket implements IServerComm {

	private InetSocketAddress address;

	public ServerCommSocket(String serviceIp, int serverPort) {
		address = InetSocketAddress.createUnresolved(serviceIp, serverPort);
	}

	public <T> T sendMsg(JSONObject msg, IContentConverter<T> converter) {

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
		Socket socket = new Socket();
		PrintWriter out = null;
		BufferedReader reader = null;
		try {
			socket.connect(address);
			out = new PrintWriter(socket.getOutputStream());
			out.write(msg.toString());
			// FIXME: read data from server as byte[], not plain text
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuilder content = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
			return converter.convert(content.toString().getBytes());
		} catch (IOException e) {
			throw new CommunicationException(e);
		} catch (JSONException e) {
			throw new InvalidatedServerDataException(e);
		} catch (Exception e) {
			throw new InvalidatedServerDataException(e);
		} finally {

			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}

		}
	}

	public JSONObject sendMsg(JSONObject msg) {

		return sendMsg(msg, C.jsonc);

	}

}
