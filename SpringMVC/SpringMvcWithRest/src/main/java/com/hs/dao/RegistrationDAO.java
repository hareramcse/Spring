package com.hs.dao;

import java.util.List;

import com.hs.vo.RegistrationVO;

public interface RegistrationDAO {
	public RegistrationVO insertData(RegistrationVO registrationVO);
	public List<RegistrationVO> getData();
}
