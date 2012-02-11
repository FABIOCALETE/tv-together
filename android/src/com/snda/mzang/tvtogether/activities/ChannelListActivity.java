package com.snda.mzang.tvtogether.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.snda.mzang.tvtogether.model.ChannelInfo;
import com.snda.mzang.tvtogether.utils.C;
import com.snda.mzang.tvtogether.utils.ui.WaitingDialogAsyncTask;

public class ChannelListActivity extends ListActivity {

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

	class LoadChannelListTask extends WaitingDialogAsyncTask<String, Integer, List<ChannelInfo>> {

		public LoadChannelListTask(Context context, String waitingMsg) {
			super(context, waitingMsg);
		}

		ProgressDialog waitingDialog;

		@Override
		protected List<ChannelInfo> process(final String oneRes) {
			try {

				String imageRoot = C.CHANNEL_RES_DIR;

				File dir = new File(imageRoot);
				if (dir.exists() == false || dir.isDirectory() == false) {
					dir.mkdirs();
				}

				URL url = ChannelListActivity.this.getClassLoader().getResource(oneRes);

				String path = url.getPath();

				String jarFilePath = path.substring(path.indexOf('/'), path.indexOf('!'));

				List<String> resName = new ArrayList<String>();

				JarFile file = new JarFile(jarFilePath);

				Enumeration<JarEntry> ets = file.entries();

				String imageDir = oneRes.substring(0, oneRes.lastIndexOf('/') + 1);

				while (ets.hasMoreElements() == true) {
					JarEntry jarEntry = ets.nextElement();
					String jarEntryPath = jarEntry.getName();
					if (jarEntryPath.startsWith(imageDir) == false) {
						continue;
					}
					InputStream input = file.getInputStream(jarEntry);
					String imgFileName = jarEntryPath.substring(jarEntryPath.lastIndexOf('/') + 1, jarEntryPath.lastIndexOf('.'));
					resName.add(imgFileName);
					File dataFile = new File(dir, imgFileName);
					if (dataFile.exists() == false || dataFile.isFile() == false) {
						dataFile.createNewFile();
					}
					OutputStream os = new FileOutputStream(dataFile);
					byte[] buffer = new byte[1024];
					int len = -1;
					while ((len = input.read(buffer)) != -1) {
						os.write(buffer, 0, len);
					}
					input.close();
					os.close();
				}

				List<ChannelInfo> images = new ArrayList<ChannelInfo>(resName.size());

				for (int i = 0; i < resName.size(); i++) {
					images.add(new ChannelInfo(resName.get(i), BitmapFactory.decodeFile(imageRoot + "/" + resName.get(i))));
				}

				return images;
			} catch (Exception ex) {
				return null;
			}
		}

		@Override
		protected void postProcess(List<ChannelInfo> result) {
			if (result == null) {
				return;
			}
			String[] names = new String[result.size()];
			for (int i = 0; i < result.size(); i++) {
				names[i] = result.get(i).getName();
			}
			ChannelListActivity.this.setListAdapter(new ChannelItemAdapter(ChannelListActivity.this, names, result));
		}
	}

	public class ChannelItemAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final List<ChannelInfo> channels;

		public ChannelItemAdapter(Context context, String[] names, List<ChannelInfo> channels) {
			super(context, R.layout.channelfragment, names);
			this.channels = channels;
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.channelfragment, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.label);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			textView.setText(channels.get(position).getName());

			imageView.setImageBitmap(channels.get(position).getIcon());

			return rowView;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// string
		Object item = getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}

}
