package com.deepnighttwo.testimagelist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class TestimagelistActivity extends ListActivity {

	public static Bitmap[] images = null;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		try {
			createImageFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setTitle("测试List");
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2" };
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
		setListAdapter(adapter);
	}

	private void createImageFiles() throws IOException {
		String imageRoot = "/sdcard/tempdir";
		File dir = new File(imageRoot);
		if (dir.exists() == false || dir.isDirectory() == false) {
			dir.mkdir();
		}
		String[] res = new String[] { "com/deepnighttwo/testimagelist/res/braid.JPG", "com/deepnighttwo/testimagelist/res/chrome.bmp",
				"com/deepnighttwo/testimagelist/res/ginger_bread.jpg", "com/deepnighttwo/testimagelist/res/github.jpg", "com/deepnighttwo/testimagelist/res/gomboc.bmp" };
		String[] resName = new String[] { "a.jpg", "b.png", "c.jpg", "d.jpg", "e.bmp" };
		for (int i = 0; i < res.length; i++) {
			InputStream input = this.getClassLoader().getResourceAsStream(res[i]);
			File dataFile = new File(dir, resName[i]);
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

		images = new Bitmap[resName.length];

		for (int i = 0; i < resName.length; i++) {
			images[i] = BitmapFactory.decodeFile(imageRoot + "/" + resName[i]);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}
}