package com.hs.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegistrationVOList {

	List<RegistrationVO> RegList;

	public void setRegList(List<RegistrationVO> regList) {
		RegList = regList;
	}

	public List<RegistrationVO> getRegList() {
		return RegList;
	}
}
