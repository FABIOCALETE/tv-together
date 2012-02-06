package com.snda.mzang.tvtogether.utils;

import com.snda.mzang.tvtogether.utils.comm.IServerComm;
import com.snda.mzang.tvtogether.utils.comm.ServerCommMock;

public interface Constants {

	String TAG = "TVTogether";

	IServerComm comm = new ServerCommMock();

}
