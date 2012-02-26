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

		int len = buffer.getInt(buffer.readerIndex());

		if (avaliable >= len + SC.lenStr) {
			buffer.readInt();
			String type = readType(buffer);
			byte[] currContent = new byte[len - SC.lenType];
			buffer.readBytes(currContent);
			CommPackage commPackage = new CommPackage();
			commPackage.type = type;
			commPackage.data = new JSONObject(new String(currContent));
			return commPackage;
		} else {
			return null;
		}
	}

	private static String readType(ChannelBuffer buffer) {
		ChannelBuffer typeBytes = buffer.readBytes(SC.lenType);
		String typeStr = new String(typeBytes.array());
		return typeStr.trim().toLowerCase();

	}
}
