package com.snda.mzang.tvtogether.server.dao.test;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.snda.mzang.tvtogether.server.dao.UserInfoDao;
import com.snda.mzang.tvtogether.server.entry.UserInfo;
import com.snda.mzang.tvtogether.server.entry.UserInfo.UserStatus;

public class UserInfoDaoTest extends BaseDaoTest {

	public static String id = "asdfalkerjhfdbjks56463215661elrwjfsldk";

	@Test
	public void testInsert() {
		UserInfoDao dao = new UserInfoDao();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setUserName("TestUser");
		userInfo.setUserPassword("this is the fucking password.");
		userInfo.setComments("");
		userInfo.setFavor("");
		userInfo.setIcon("1.png");
		userInfo.setLastLogin(new Date());
		userInfo.setLocationX(0.0);
		userInfo.setLocationY(0.0);
		userInfo.setRegisterTime(new Date());
		userInfo.setStatus(UserStatus.ENABLE.getStatus());
		Assert.assertTrue(dao.insertUser(userInfo));
	}

	@Test
	public void testUpdate() {
		UserInfoDao dao = new UserInfoDao();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setUserName("TestUse2 - 2");
		userInfo.setUserPassword("this is the fucking password.");
		userInfo.setComments("");
		userInfo.setFavor("");
		userInfo.setIcon("1.png");
		userInfo.setLastLogin(new Date());
		userInfo.setLocationX(0.0);
		userInfo.setLocationY(0.0);
		userInfo.setRegisterTime(new Date());
		userInfo.setStatus(UserStatus.ENABLE.getStatus());
		Assert.assertTrue(dao.updatetUser(userInfo));
	}

	@Test
	public void testLogin() {
		UserInfoDao dao = new UserInfoDao();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setUserName("TestUse2 - 2");
		userInfo.setUserPassword("this is the fucking password.");
		Assert.assertNotNull(dao.login(userInfo));
	}

	@Test
	public void testSelect() {
		UserInfoDao dao = new UserInfoDao();
		Assert.assertNotNull(dao.selectUser(id));
	}

	@Test
	public void testDelete() {
		UserInfoDao dao = new UserInfoDao();
		Assert.assertTrue(dao.deleteUser(id));
	}

}
