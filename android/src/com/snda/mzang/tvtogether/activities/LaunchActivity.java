package com.snda.mzang.tvtogether.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.snda.mzang.tvtogether.R;
import com.snda.mzang.tvtogether.utils.C;
import com.snda.mzang.tvtogether.utils.JSONUtil;
import com.snda.mzang.tvtogether.utils.db.DBUtil;
import com.snda.mzang.tvtogether.utils.res.ResUtil;
import com.snda.mzang.tvtogether.utils.ui.WaitingDialogAsyncTask;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.launch);
		// MockServer.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
			// Bundle bundle = new Bundle();
			// bundle.putString("demoMsg", msg);
			// intent.putExtras(bundle);
			startActivity(intent);
			LaunchActivity.this.finish();

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

				ResUtil.getResAs(C.CHANNEL_RES_DIR + channelName, null, false);

			}
		} catch (Exception ex) {
			return;
		}

	}
}
