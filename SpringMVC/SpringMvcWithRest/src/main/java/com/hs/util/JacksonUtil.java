package com.hs.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.vo.RegistrationVO;

public class JacksonUtil {

	public static void mapToJsonConvert(Map<Object, Object> map, List<Object> jsonList) {
		ObjectMapper mapper = new ObjectMapper();
		Writer writer = new StringWriter();
		try {
			mapper.writeValue(writer, map);
			jsonList.add(writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object convertJsonToObject(String json) {

		ObjectMapper objectMapper = new ObjectMapper();
		Reader reader = new StringReader(json);
		RegistrationVO rvo = null;
		try {
			rvo = objectMapper.readValue(reader, RegistrationVO.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rvo;
	}
}
