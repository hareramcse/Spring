package com.hs.methodreplacer;

import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;

public class FormatMessageReplacer implements MethodReplacer {

	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		if (isFormatMessageMethod(method)) {

			String msg = (String) args[0]+" method replacer";

			return msg;
		} else {
			throw new IllegalArgumentException("Unable to reimplement method " + method.getName());
		}
	}

	private boolean isFormatMessageMethod(Method method) {

		// check correct number of params
		if (method.getParameterTypes().length != 1) {
			return false;
		}

		// check method name
		if (!("formatMessage".equals(method.getName()))) {
			return false;
		}

		// check return type
		if (method.getReturnType() != String.class) {
			return false;
		}

		// check parameter type is correct
		if (method.getParameterTypes()[0] != String.class) {
			return false;
		}

		return true;
	}
}