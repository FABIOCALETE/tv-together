package com.snda.mzang.tvtogether.mockupserver.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.mockupserver.CommPackage;
import com.snda.mzang.tvtogether.mockupserver.SC;

public class ContentDecoder extends FrameDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		int avaliable = buffer.readableBytes();

		if (avaliable < SC.lenHeader) {
			return null;
		}

		int len = getLength(buffer);

		String type = getType(buffer);

		if (avaliable >= len + SC.lenHeader) {
			buffer.readerIndex(SC.lenHeader);
			byte[] currContent = new byte[len - SC.lenHeader];
			buffer.readBytes(currContent);
			CommPackage commPackage = new CommPackage();
			commPackage.type = type;
			commPackage.data = new JSONObject(new String(currContent));
			return commPackage;
		} else {
			return null;
		}
	}

	private static int getLength(ChannelBuffer buffer) {
		byte[] lenBytes = new byte[SC.lenStr];
		buffer.getBytes(0, lenBytes);
		String lenStrc = new String(lenBytes);
		return Integer.valueOf(lenStrc.trim());
	}

	private static String getType(ChannelBuffer buffer) {
		byte[] typeBytes = new byte[SC.lenType];
		buffer.getBytes(SC.lenStr, typeBytes);
		String typeStr = new String(typeBytes);
		return typeStr.trim().toLowerCase();

	}
}
