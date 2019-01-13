package com.hs.controller;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hs.dao.DAOService;
import com.hs.service.EmailService;
import com.hs.service.SMSService;

@RestController
public class TwoFactorServiceController {

	@Autowired
	EmailService emailService;

	@Autowired
	DAOService daoService;

	@Autowired
	SMSService smsService;

	@RequestMapping(value = "/users/{userid}/emails/{emailid}/tfa", method = RequestMethod.PUT)
	public ResponseEntity<Object> send2faCodeinEmail(@PathVariable("userid") String id,
			@PathVariable("emailid") String emailid) throws AddressException, MessagingException {
		String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
		emailService.sendEmail(emailid, twoFaCode);
		daoService.update2FAProperties(id, twoFaCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{userid}/mobilenumbers/{mobilenumber}/tfa", method = RequestMethod.PUT)
	public ResponseEntity<Object> send2faCodeinSMS(@PathVariable("userid") String id,
			@PathVariable("mobilenumber") String mobile) {
		String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
		smsService.send2FaCode(mobile, twoFaCode);
		daoService.update2FAProperties(id, twoFaCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{userid}/codes/{tfacode}", method = RequestMethod.PUT)
	public ResponseEntity<Object> verify(@PathVariable("userid") String id, @PathVariable("tfacode") String code) {

		boolean isValid = daoService.checkCode(id, code);

		if (isValid)
			return new ResponseEntity<>(HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
