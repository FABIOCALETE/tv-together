package com.snda.mzang.tvtogether.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.snda.mzang.tvtogether.utils.C;
import com.snda.mzang.tvtogether.utils.JSONUtil;
import com.snda.mzang.tvtogether.utils.db.DBUtil;
import com.snda.mzang.tvtogether.utils.ui.WaitingDialogAsyncTask;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LaunchTvTogetherTask initTask = new LaunchTvTogetherTask(null, "");
		initTask.execute(new Object());
	}

	class LaunchTvTogetherTask extends WaitingDialogAsyncTask<Object, Object> {

		public LaunchTvTogetherTask(Context context, String waitingMsg) {
			super(context, waitingMsg);
		}

		@Override
		protected Object process(Object param) {

			initDB();

			initResourceFiles();

			return null;
		}

		@Override
		protected void postProcess(Object result) {

		}

	}

	private void initDB() {
		DBUtil.initDB(this);
	}

	private void initResourceFiles() {
		try {
			JSONObject reqChannelList = new JSONObject();
			reqChannelList.put(C.handler, C.getChannelListHandler);

			JSONObject ret = C.comm.sendMsg(reqChannelList);
			String[] channelNames = JSONUtil.getStringArray(ret, C.channels);

			for (String channelName : channelNames) {
				JSONObject reqServerRes = new JSONObject();
				reqServerRes.put(C.handler, C.getServerResource);
				reqServerRes.put(C.resPathOnServ, C.CHANNEL_RES_DIR + channelName);
				byte[] content = C.comm.sendMsg(reqServerRes, null);
				File resFile = new File(C.sdroot + C.CHANNEL_RES_DIR + channelName);
				if (resFile.isFile() == false) {
					resFile.createNewFile();
				}
				OutputStream out = new FileOutputStream(resFile);
				out.write(content);
				out.flush();
				out.close();
			}
		} catch (Exception ex) {
			return;
		}

	}
}
