package com.snda.mzang.tvtogether.activities;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.snda.mzang.tvtogether.base.JSONUtil;
import com.snda.mzang.tvtogether.utils.C;
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
		this.setTitle(R.string.channel_channelList_title);

		buildChannelList();

	}

	public void buildChannelList() {

		LoadChannelListTask task = new LoadChannelListTask(this, getText(R.string.channel_loading_msg).toString());
		task.execute((String) null);
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

			String iconFileStr = C.CHANNEL_RES_LOCAL_DIR + names[position];
			Bitmap icon = bitmapCache.get(iconFileStr);
			if (icon == null) {
				File iconFile = new File(iconFileStr);
				if (iconFile.isFile()) {
					icon = BitmapFactory.decodeFile(iconFileStr);
					bitmapCache.put(iconFileStr, icon);
				}
			}
			imageView.setImageBitmap(icon);
			return rowView;
		}
	}

	@Override
	public void onBackPressed() {

		Dialog dialog = new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setTitle(R.string.channel_exit_title).setMessage(R.string.channel_exit_msg)
				.setPositiveButton(R.string.channel_exit, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						ChannelListActivity.this.finish();
					}
				}).setNeutralButton(R.string.channel_not_exit, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).create();
		dialog.show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// string
		Object item = getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
	}

}
