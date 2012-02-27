package com.snda.mzang.tvtogether.server.entry;

import java.util.Date;

public class UserInfo {

	public static enum UserStatus {
		ENABLE("enable"), DISABLE("disable");

		private String value;

		UserStatus(String value) {
			this.value = value;
		}

		public String getStatus() {
			return value;
		}

		public UserStatus getStatus(String value) {
			if ("enable".equals(value)) {
				return ENABLE;
			} else if ("disable".equals(value)) {
				return DISABLE;
			}
			return null;
		}

		public String toString() {
			return value;
		}
	}

	private String id;
	private String userName;
	private String userPassword;
	private String icon;
	private String comments;
	private String favor;
	private Double locationX;
	private Double locationY;
	private Date registerTime;
	private Date lastLogin;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFavor() {
		return favor;
	}

	public void setFavor(String favor) {
		this.favor = favor;
	}

	public Double getLocationX() {
		return locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public Double getLocationY() {
		return locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
