package com.snda.mzang.tvtogether.server.dao;

import org.apache.ibatis.session.SqlSession;

import com.snda.mzang.tvtogether.server.util.SC;

public class BaseDao {

	protected SqlSession getSqlSession() {
		return SC.sqlFactory.openSession(true);
	}

}
