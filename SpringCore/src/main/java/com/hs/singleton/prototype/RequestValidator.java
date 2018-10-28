package com.hs.singleton.prototype;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
	 
    private List<String> errorMessages = new ArrayList<String>();
 
    public RequestValidator() {
        System.out.println("RequestValidator constructor");
    }

    public void validate(String requestId){
 
    }
 
    public List<String> getErrorMessages() {
        return errorMessages;
    }
 
}