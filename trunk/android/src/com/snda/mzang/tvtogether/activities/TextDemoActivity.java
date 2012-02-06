package com.snda.mzang.tvtogether.activities;

import com.snda.mzang.tvtogether.R;
import com.snda.mzang.tvtogether.R.id;
import com.snda.mzang.tvtogether.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TextDemoActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);

		Bundle extras = getIntent().getExtras();

		TextView demoText = (TextView) this.findViewById(R.id.demoText);

		demoText.setText(extras.getString("demoMsg"));
	}

}
