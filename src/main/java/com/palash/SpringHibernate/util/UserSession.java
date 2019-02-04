package com.palash.SpringHibernate.util;

import java.util.Date;

public class UserSession {
	private int UserID;
	private String UserName;
	private String FullName;
	private String Email;
	private String Roll;
	private boolean IsActive;
	private Date LoginTime;
	private boolean Logged;
	public UserSession() {
		super();
		this.Logged=false;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getRoll() {
		return Roll;
	}
	public void setRoll(String roll) {
		Roll = roll;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public Date getLoginTime() {
		return LoginTime;
	}
	public void setLoginTime(Date loginTime) {
		LoginTime = loginTime;
	}
	public boolean isLogged() {
		return Logged;
	}
	public void setLogged(boolean logged) {
		Logged = logged;
	}
	@Override
	public String toString() {
		return "UserSession [UserID=" + UserID + ", UserName=" + UserName + ", FullName=" + FullName + ", Email="
				+ Email + ", Roll=" + Roll + ", IsActive=" + IsActive + ", LoginTime=" + LoginTime + ", Logged="
				+ Logged + "]";
	}
	
	
}
