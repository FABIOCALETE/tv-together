package com.snda.mzang.tvtogether.server;

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
import com.snda.mzang.tvtogether.exception.L;
import com.snda.mzang.tvtogether.server.handler.processor.GetChannelListHandlerMockup;
import com.snda.mzang.tvtogether.server.handler.processor.GetServerResourceMockup;
import com.snda.mzang.tvtogether.server.handler.processor.IMessageHandler;
import com.snda.mzang.tvtogether.server.handler.processor.IValidationHandler;
import com.snda.mzang.tvtogether.server.handler.processor.LoginHandlerMockup;

public class CommPackageProcessor {

	private static Map<String, IMessageHandler> processors = new HashMap<String, IMessageHandler>();

	public CommPackageProcessor() {
		IMessageHandler handler = new LoginHandlerMockup();
		processors.put(handler.getHandlerName().toLowerCase(), handler);

		handler = new GetChannelListHandlerMockup();
		processors.put(handler.getHandlerName().toLowerCase(), handler);

		handler = new GetServerResourceMockup();
		processors.put(handler.getHandlerName().toLowerCase(), handler);

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
			IMessageHandler handler = processors.get(handlerName);
			if (handler == null) {
				throw new InvalidatedServerDataException("No handler found for name \"" + handlerName + "\"");
			}

			if (handler instanceof IValidationHandler) {
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

			// URL url =
			// GetChannelListHandlerMockup.class.getClassLoader().getResource(oneRes);
			//
			// String path = url.getPath();
			//
			// String jarFilePath = path.substring(path.indexOf('/'),
			// path.indexOf('!'));
			//
			// List<String> resName = new ArrayList<String>();
			//
			// JarFile file = new JarFile(jarFilePath);
			//
			// Enumeration<JarEntry> ets = file.entries();
			//
			// String imageDir = oneRes.substring(0, oneRes.lastIndexOf('/') +
			// 1);
			//
			// while (ets.hasMoreElements() == true) {
			// JarEntry jarEntry = ets.nextElement();
			// String jarEntryPath = jarEntry.getName();
			// if (jarEntryPath.startsWith(imageDir) == false) {
			// continue;
			// }
			// InputStream input = file.getInputStream(jarEntry);
			// String imgFileName =
			// jarEntryPath.substring(jarEntryPath.lastIndexOf('/') + 1,
			// jarEntryPath.lastIndexOf('.'));
			// resName.add(imgFileName);
			// File dataFile = new File(dir, imgFileName);
			// if (dataFile.exists() == false || dataFile.isFile() == false) {
			// dataFile.createNewFile();
			// }
			// OutputStream os = new FileOutputStream(dataFile);
			// byte[] buffer = new byte[1024];
			// int len = -1;
			// while ((len = input.read(buffer)) != -1) {
			// os.write(buffer, 0, len);
			// }
			// input.close();
			// os.close();
			// }

			// channelNames = resName.toArray(new String[0]);

			// BitmapFactory.decodeFile()

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
