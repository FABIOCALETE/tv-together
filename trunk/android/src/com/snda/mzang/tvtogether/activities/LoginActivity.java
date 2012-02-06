package com.snda.mzang.tvtogether.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.snda.mzang.tvtogether.R;
import com.snda.mzang.tvtogether.utils.ui.PopupTipsUtil;

public class LoginActivity extends Activity {

	private static boolean action = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		Button loginBtn = (Button) this.findViewById(R.id.loginBtn);

		final EditText userName = (EditText) this.findViewById(R.id.loginUsername);

		final EditText password = (EditText) this.findViewById(R.id.loginPassword);

		final CheckBox regNewUser = (CheckBox) this.findViewById(R.id.loginReg);

		final CheckBox keepLogin = (CheckBox) this.findViewById(R.id.loginKeepLogin);

		loginBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				final String msg = constuctLoginMessage(userName.getText().toString(), password.getText().toString(), regNewUser.isChecked(), keepLogin.isChecked());

				PopupTipsUtil.showWaitingDialog(LoginActivity.this, new Runnable() {

					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}, "正在注册中...");
				// Intent intent = new Intent(getApplicationContext(),
				// TextDemoActivity.class);
				// Bundle bundle = new Bundle();
				// bundle.putString("demoMsg", msg);
				// intent.putExtras(bundle);
				// startActivity(intent);

				// String phoneNum = "2323232323";// 电话号码
				// Intent intent = new Intent();
				// intent.setAction(Intent.ACTION_DIAL);
				// intent.setData(Uri.parse("tel:" + phoneNum));
				// startActivity(intent);

			}

		});
	}

	public String constuctLoginMessage(String userName, String password, boolean regNewUser, boolean keepLogin) {
		JSONObject login = new JSONObject();
		StringBuilder content = new StringBuilder();
		content.append(userName).append("; ").append(password).append("; ").append(regNewUser).append("; ").append(keepLogin);
		return content.toString();
	}
}
