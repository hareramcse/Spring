package com.hs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.service.RegistrationService;
import com.hs.util.JacksonUtil;
import com.hs.vo.RegistrationVO;
import com.hs.vo.RegistrationVOList;

@Controller
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration";
	}

	@RequestMapping(value = "/insertRegistrationDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveRegistrationDetails(
			@RequestBody RegistrationVO registrationVO) {
		registrationService.insertData(registrationVO);
		System.out.println("inserted");
		return "success";
	}

	@RequestMapping(value = "/insertRegistrationDetailsAsXML", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String postRegistrationDetails(
			@RequestBody RegistrationVOList registrationVOList) {
		System.out.println("calling postRegistrationDetails");
		List<RegistrationVO> registrationVOs = registrationVOList.getRegList();

		for (RegistrationVO registrationVO : registrationVOs) {
			registrationService.insertData(registrationVO);
		}
		System.out.println("xml data inserted");
		return "xml success";
	}

	@RequestMapping(value = "/insertData", method = RequestMethod.GET)
	@ResponseBody
	public String insertData(HttpServletRequest request) {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String profession = request.getParameter("profession");
		String age = request.getParameter("age");
		String mobile = request.getParameter("mobile");

		RegistrationVO registrationVO = new RegistrationVO();
		registrationVO.setUserName(userName);
		registrationVO.setPassword(password);
		registrationVO.setAddress(address);
		registrationVO.setProfession(profession);
		registrationVO.setAge(Integer.parseInt(age));
		registrationVO.setMobile(Long.parseLong(mobile));

		registrationVO = registrationService.insertData(registrationVO);
		System.out.println("data inserted");
		return "data inserted";
	}

	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	@ResponseBody
	public List<RegistrationVO> getData() throws JAXBException {
		List<RegistrationVO> registrationVO = registrationService.getData();
		return registrationVO;
	}

	@RequestMapping(value = "/getCustomDataAsJsonString", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getDataAsJsonString() {
		List<RegistrationVO> registrationVO = registrationService.getData();
		return convertListToString(registrationVO);
	}

	private List<Object> convertListToString(List<RegistrationVO> list) {
		List<Object> jsonList = new ArrayList<Object>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (RegistrationVO vo : list) {
			map.put("userName", vo.getUserName());
			map.put("password", vo.getPassword());
			map.put("address", vo.getAddress());
			map.put("profession", vo.getProfession());
			map.put("city", "bangalore");
			JacksonUtil.mapToJsonConvert(map, jsonList);
		}
		return jsonList;
	}

	@RequestMapping(value = "/getDataAsXML", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public RegistrationVOList getDataAsXML() throws JAXBException {
		List<RegistrationVO> registrationVO = registrationService.getData();
		RegistrationVOList rvoList = new RegistrationVOList();
		rvoList.setRegList(registrationVO);
		return rvoList;
	}
}
