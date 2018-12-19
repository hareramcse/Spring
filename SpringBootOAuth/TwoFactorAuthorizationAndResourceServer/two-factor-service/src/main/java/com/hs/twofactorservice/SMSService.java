package com.hs.twofactorservice;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SMSService {

	private final static String ACCOUNT_SID = "AC5447c4d4f2ee70426ffd396b553873b6";
	private final static String AUTH_ID = "31b0155fbbed38747fcac5878c6a1356";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	public boolean send2FaCode(String mobilenumber, String twoFaCode) {

		Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("+17604900310"),
				"Your Two Factor Authentication code is: " + twoFaCode).create();

		return true;

	}
}
