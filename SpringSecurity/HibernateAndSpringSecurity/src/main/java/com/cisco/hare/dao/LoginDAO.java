package com.cisco.hare.dao;

import com.cisco.hare.model.Users;

public interface LoginDAO {
	 Users findByUserName(String username);
	}
