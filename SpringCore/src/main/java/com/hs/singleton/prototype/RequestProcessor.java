package com.hs.singleton.prototype;

public class RequestProcessor {

	private RequestValidator validator;

	public void handleRequest(String requestId) {
		validator.validate(requestId);
	}

	RequestProcessor() {
		System.out.println("RequestProcessor constructor");
	}

	public RequestValidator getValidator() {
		return validator;
	}

	public void setValidator(RequestValidator validator) {
		this.validator = validator;
	}
}