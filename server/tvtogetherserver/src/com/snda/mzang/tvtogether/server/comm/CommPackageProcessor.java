package com.snda.mzang.tvtogether.server.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.snda.mzang.tvtogether.base.B;
import com.snda.mzang.tvtogether.base.JSONUtil;
import com.snda.mzang.tvtogether.exception.InvalidatedServerDataException;
import com.snda.mzang.tvtogether.server.handler.processor.GetChannelList;
import com.snda.mzang.tvtogether.server.handler.processor.GetServerResource;
import com.snda.mzang.tvtogether.server.handler.processor.Login;
import com.snda.mzang.tvtogether.server.log.L;
import com.snda.mzang.tvtogether.server.util.SC;

public class CommPackageProcessor {

	private static Map<String, IMessageProcessor> processors = new HashMap<String, IMessageProcessor>();

	public CommPackageProcessor() {
		IMessageProcessor handler = new Login();
		processors.put(handler.getProcessorName().toLowerCase(), handler);

		handler = new GetChannelList();
		processors.put(handler.getProcessorName().toLowerCase(), handler);

		handler = new GetServerResource();
		processors.put(handler.getProcessorName().toLowerCase(), handler);

	}

	public byte[] process(CommPackage commPkg) {

		if (commPkg == null) {
			L.error("No message.");
			return new byte[0];
		}
		if (StringUtils.isEmpty(commPkg.type) == true) {
			return "No hanlder".getBytes();
		}

		try {
			String handlerName = commPkg.type;
			IMessageProcessor handler = processors.get(handlerName);
			if (handler == null) {
				throw new InvalidatedServerDataException("No handler found for name \"" + handlerName + "\"");
			}

			if (handler instanceof IValidationProcessor) {
				if (doLoginValidation(JSONUtil.getString(commPkg.data, B.username), JSONUtil.getString(commPkg.data, B.password)) == false) {
					return "User validation failed".getBytes();
				}
			}
			byte[] serverContent = handler.handle(commPkg.data);
			return serverContent;

		} catch (Exception e) {
			e.printStackTrace();
			return new byte[0];
		}

	}

	private static boolean doLoginValidation(String userName, String password) {
		// FIXME: do validation
		return true;
	}

	public static Map<String, byte[]> cache = new HashMap<String, byte[]>();

	public static String[] channelNames = null;

	public static String[] loadChannelInfos() {
		if (channelNames != null) {
			return channelNames;
		}
		try {

			String imageRoot = SC.resBase + B.CHANNEL_RES_DIR;

			File dir = new File(imageRoot);
			if (dir.exists() == false || dir.isDirectory() == false) {
				dir.mkdirs();
			}

			File[] files = dir.listFiles();

			List<String> resNamesList = new ArrayList<String>();

			for (File dataFile : files) {
				InputStream input = new FileInputStream(dataFile);
				byte[] fileData = new byte[(int) dataFile.length()];
				input.read(fileData);
				String fileName = dataFile.getName();
				int last = fileName.lastIndexOf('.') > 0 ? fileName.lastIndexOf('.') : fileName.length();
				String resName = fileName.substring(0, last);
				resNamesList.add(resName);
				cache.put(SC.resBase + B.CHANNEL_RES_DIR + resName, fileData);
				input.close();
			}
			channelNames = resNamesList.toArray(new String[0]);
			return channelNames;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
