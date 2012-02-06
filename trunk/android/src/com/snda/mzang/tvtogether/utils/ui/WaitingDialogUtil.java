package com.snda.mzang.tvtogether.utils.ui;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.app.ProgressDialog;

public class WaitingDialogUtil {

	public static void showWaitingDialog(final Activity parent, final Runnable runnable, String msg) {

		if (StringUtils.isEmpty(msg)) {
			msg = "���ڴ�����...";
		}

		final ProgressDialog waitingDialog = ProgressDialog.show(parent, "��ȴ�...", msg, true);

		ThreadPoolUtil.executeInThread(new Runnable() {

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

}
