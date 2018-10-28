package com.hs.service;

import java.util.List;

import com.hs.vo.RegistrationVO;

public interface RegistrationService {
	public RegistrationVO insertData(RegistrationVO registrationVO);
	public List<RegistrationVO> getData();
}
