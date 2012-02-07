package com.snda.mzang.tvtogether.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.snda.mzang.tvtogether.R;
import com.snda.mzang.tvtogether.utils.Constants;
import com.snda.mzang.tvtogether.utils.JSONUtil;
import com.snda.mzang.tvtogether.utils.UserSession;
import com.snda.mzang.tvtogether.utils.ui.PopupTipsUtil;
import com.snda.mzang.tvtogether.utils.ui.WaitingDialogAsyncTask;

public class LoginActivity extends Activity {

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
				final JSONObject msg = constuctLoginMessage(userName.getText().toString(), password.getText().toString(), regNewUser.isChecked(), keepLogin.isChecked());

				LoginTask task = new LoginTask(null, false, null);
				task.execute(msg);
				// Intent intent = new Intent(getApplicationContext(),
				// TextDemoActivity.class);
				// Bundle bundle = new Bundle();
				// bundle.putString("demoMsg", msg);
				// intent.putExtras(bundle);
				// startActivity(intent);

				// String phoneNum = "2323232323";
				// Intent intent = new Intent();
				// intent.setAction(Intent.ACTION_DIAL);
				// intent.setData(Uri.parse("tel:" + phoneNum));
				// startActivity(intent);

			}

		});
	}

	class LoginTask extends WaitingDialogAsyncTask<JSONObject, Integer, JSONObject> {

		public LoginTask(Context context, boolean showWaitingDialog, String waitingMsg) {
			super(context, showWaitingDialog, waitingMsg);
		}

		ProgressDialog waitingDialog;

		@Override
		protected JSONObject process(final JSONObject data) {
			boolean keepLoginBoolean = JSONUtil.getBoolean(data, "keepLogin");
			if (keepLoginBoolean == true) {
				Log.d(Constants.TAG, "Need to store user setting");
			}

			JSONObject ret = Constants.comm.sendMsg(data);
			String content = JSONUtil.getString(ret, "result");
			Log.d(Constants.TAG, content);

			return ret;
		}

		@Override
		protected void postProcess(JSONObject result) {
			String displayMsg = "=====\r\n" + result.toString() + "\r\n=====";
			PopupTipsUtil.displayToast(LoginActivity.this, displayMsg);
			Intent intent = new Intent(getApplicationContext(), TextDemoActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("demoMsg", displayMsg);
			intent.putExtras(bundle);
			startActivity(intent);
		}

	}

	public JSONObject constuctLoginMessage(String userName, String password, boolean regNewUser, boolean keepLogin) {
		JSONObject login = new JSONObject();

		UserSession.setUserName(userName);
		UserSession.setPassword(password);
		try {
			login.put("handler", "loginHandler");
			login.put("keepLogin", keepLogin);
			login.put("regNewUser", regNewUser);
			return login;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
