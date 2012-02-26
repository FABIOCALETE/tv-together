package com.snda.mzang.tvtogether.mockupserver.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.snda.mzang.tvtogether.mockupserver.CommPackage;
import com.snda.mzang.tvtogether.mockupserver.CommPackageProcessor;

public class RequestHandler extends SimpleChannelHandler {

	public static final RequestHandler INSTANCE = new RequestHandler();

	private RequestHandler() {

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Channel channel = e.getChannel();
		CommPackage msg = (CommPackage) e.getMessage();
		System.out.println("Message Recieved:" + msg);

		byte[] ret = CommPackageProcessor.process(msg);

		ChannelBuffer buff = ChannelBuffers.copiedBuffer(ret);

		ChannelFuture future = channel.write(buff);

		future.addListener(new ChannelFutureListener() {

			public void operationComplete(ChannelFuture future) throws Exception {
				future.getChannel().close();
			}

		});
		super.messageReceived(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		ctx.getChannel().close();
	}
}