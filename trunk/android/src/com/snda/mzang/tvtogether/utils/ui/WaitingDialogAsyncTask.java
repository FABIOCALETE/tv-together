package com.snda.mzang.tvtogether.utils.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

public abstract class WaitingDialogAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

	private ProgressDialog waitingDialog;

	private final Handler uiHandler;

	private boolean showWaitingDialog;

	private Context context;

	private String waitingMsg;

	public WaitingDialogAsyncTask(final Context context, Handler uiHandler, boolean showWaitingDialog, String waitingMsg) {
		this.context = context;
		this.uiHandler = uiHandler;
		this.showWaitingDialog = showWaitingDialog;
		this.waitingMsg = waitingMsg;
	}

	public WaitingDialogAsyncTask(final Context context, boolean showWaitingDialog, String waitingMsg) {
		this.context = context;
		this.showWaitingDialog = showWaitingDialog;
		if (showWaitingDialog == true) {
			this.uiHandler = new Handler();
		} else {
			uiHandler = null;
		}
		this.waitingMsg = waitingMsg;
	}

	protected abstract Result process(Params param);

	@Override
	protected Result doInBackground(final Params... params) {
		if (params.length == 0 || params[0] == null) {
			return null;
		}
		final Params data = params[0];

		if (uiHandler != null && context != null && showWaitingDialog == true) {
			showWaitingDialog = true;
			uiHandler.post(new Runnable() {

				public void run() {
					waitingDialog = PopupTipsUtil.showWaitingDialog(context, waitingMsg);

				}

			});
		} else {
			showWaitingDialog = false;
		}

		return process(data);
	}

	protected abstract void postProcess(Result result);

	@Override
	protected void onPostExecute(Result result) {
		if (waitingDialog != null) {
			waitingDialog.dismiss();
		} else if (showWaitingDialog == true) {
			int retry = 0;
			try {
				while (waitingDialog == null && retry < 10) {
					try {
						Thread.sleep(100);
						retry++;
					} catch (InterruptedException e) {
					}
				}
			} finally {
				if (waitingDialog != null) {
					waitingDialog.dismiss();
				}
			}
		}
		postProcess(result);
	}

}
