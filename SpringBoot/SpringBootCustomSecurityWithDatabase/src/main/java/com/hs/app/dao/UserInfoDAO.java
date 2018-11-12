package com.hs.app.dao;

import com.hs.app.entities.UserInfo;

public interface UserInfoDAO {
	public abstract UserInfo getActiveUser(String userName);
}