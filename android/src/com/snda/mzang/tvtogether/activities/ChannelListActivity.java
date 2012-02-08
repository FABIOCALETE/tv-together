package com.snda.mzang.tvtogether.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.snda.mzang.tvtogether.utils.C;
import com.snda.mzang.tvtogether.utils.JSONUtil;
import com.snda.mzang.tvtogether.utils.ui.PopupTipsUtil;
import com.snda.mzang.tvtogether.utils.ui.WaitingDialogAsyncTask;

public class ChannelListActivity extends Activity {

	// private LinearLayout list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// list = new LinearLayout(this);
		// LayoutParams ltp = new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.WRAP_CONTENT);
		// list.addView(title, ltp);
		// this.setContentView(list);
		// buildChannelList();
		this.setTitle("频道列表");

		buildChannelList();

	}

	public class ArrayListFragment extends ListFragment {

		String[] list;

		public ArrayListFragment(String[] list) {
			this.list = list;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list));
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			String channelName = (String) l.getItemAtPosition(position);
			Log.i("TVTogether", "Item clicked: " + id + ", Text: " + channelName);
			PopupTipsUtil.displayToast(ChannelListActivity.this, channelName + " is clicked");
		}
	}

	public void buildChannelList() {
		LoadChannelListTask task = new LoadChannelListTask(this, "正在载入电视频道...");
		JSONObject req = new JSONObject();
		try {
			req.put("handler", "getChannelListHandler");
			task.execute(req);
		} catch (JSONException e) {
		}
	}

	class LoadChannelListTask extends WaitingDialogAsyncTask<JSONObject, Integer, JSONObject> {

		public LoadChannelListTask(Context context, String waitingMsg) {
			super(context, waitingMsg);
		}

		ProgressDialog waitingDialog;

		@Override
		protected JSONObject process(final JSONObject data) {

			JSONObject ret = C.comm.sendMsg(data);
			String content = JSONUtil.getString(ret, C.result);
			Log.d(C.TAG, content);

			return ret;
		}

		@Override
		protected void postProcess(JSONObject result) {
			if (C.success.equalsIgnoreCase(JSONUtil.getString(result, C.result)) == false) {
				return;
			}

			// Create the list fragment and add it as our sole content.
			if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
				ArrayListFragment list = new ArrayListFragment(JSONUtil.getStringArray(result, C.channels));
				getFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
			}
		}
	}

}
