package com.snda.mzang.tvtogether;

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
