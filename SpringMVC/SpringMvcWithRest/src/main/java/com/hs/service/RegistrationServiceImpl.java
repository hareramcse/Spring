package com.hs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hs.dao.RegistrationDAO;
import com.hs.vo.RegistrationVO;

public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	RegistrationDAO registrationDAO;
	
	@Override
	public RegistrationVO insertData(RegistrationVO registrationVO) {
		return registrationDAO.insertData(registrationVO);
	}

	@Override
	public List<RegistrationVO> getData() {
		List<RegistrationVO> loginListVO=registrationDAO.getData();
		return loginListVO;
	}
	
}
