package com.snda.mzang.tvtogether.utils.ui;

import org.apache.commons.lang.StringUtils;

import com.snda.mzang.tvtogether.utils.ThreadPoolUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class PopupTipsUtil {

	public static void showWaitingDialog(final Activity parent, final Runnable runnable, String msg) {

		if (StringUtils.isEmpty(msg)) {
			msg = "正在处理中...";
		}

		final ProgressDialog waitingDialog = ProgressDialog.show(parent, "请等待...", msg, true);

		ThreadPoolUtil.execute(new Runnable() {

			public void run() {
				try {
					runnable.run();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					waitingDialog.dismiss();
				}
			}

		});

	}

	public static void displayToast(Context context, String text) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 220);
		toast.show();
	}

}
