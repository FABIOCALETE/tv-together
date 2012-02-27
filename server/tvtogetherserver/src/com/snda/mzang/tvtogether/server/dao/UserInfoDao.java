package com.snda.mzang.tvtogether.server.dao;

import com.snda.mzang.tvtogether.server.entry.UserInfo;

public class UserInfoDao extends BaseDao {

	public boolean insertUser(UserInfo userInfo) {
		return getSqlSession().insert("userInfo.insertUser", userInfo) == 1;
	}

	public boolean updatetUser(UserInfo userInfo) {
		return getSqlSession().update("userInfo.updateUser", userInfo) == 1;
	}

	public UserInfo login(UserInfo userInfo) {
		return (UserInfo) getSqlSession().selectOne("userInfo.login", userInfo);
	}

}
