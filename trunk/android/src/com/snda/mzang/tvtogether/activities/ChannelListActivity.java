package com.snda.mzang.tvtogether.activities;

import java.util.Map;
import java.util.WeakHashMap;

import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.snda.mzang.tvtogether.R;
import com.snda.mzang.tvtogether.utils.C;
import com.snda.mzang.tvtogether.utils.JSONUtil;
import com.snda.mzang.tvtogether.utils.ui.WaitingDialogAsyncTask;

public class ChannelListActivity extends ListActivity {

	Map<String, Bitmap> bitmapCache = new WeakHashMap<String, Bitmap>();

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

	public void buildChannelList() {
		LoadChannelListTask task = new LoadChannelListTask(this, "正在载入电视频道...");
		task.execute("com/snda/mzang/tvtogether/res/CCAV电视台.png");
	}

	class LoadChannelListTask extends WaitingDialogAsyncTask<String, String[]> {

		public LoadChannelListTask(Context context, String waitingMsg) {
			super(context, waitingMsg);
		}

		ProgressDialog waitingDialog;

		@Override
		protected String[] process(final String oneRes) {
			try {
				JSONObject reqChannelList = new JSONObject();
				reqChannelList.put(C.handler, C.getChannelListHandler);

				JSONObject ret = C.comm.sendMsg(reqChannelList);
				return JSONUtil.getStringArray(ret, C.channels);
			} catch (Exception ex) {
				return null;
			}
		}

		@Override
		protected void postProcess(String[] result) {
			if (result == null) {
				return;
			}
			ChannelListActivity.this.setListAdapter(new ChannelItemAdapter(ChannelListActivity.this, result));
		}
	}

	public class ChannelItemAdapter extends ArrayAdapter<String> {
		private final Context context;
		private String[] names;

		public ChannelItemAdapter(Context context, String[] names) {
			super(context, R.layout.channelfragment, names);
			this.context = context;
			this.names = names;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.channelfragment, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.label);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			textView.setText(names[position]);

			String iconFile = C.CHANNEL_RES_LOCAL_DIR + names[position];
			Bitmap icon = bitmapCache.get(iconFile);
			if (icon == null) {
				icon = BitmapFactory.decodeFile(iconFile);
				bitmapCache.put(iconFile, icon);
			}
			imageView.setImageBitmap(icon);
			return rowView;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// string
		Object item = getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
	}

}
