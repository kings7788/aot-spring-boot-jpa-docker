package com.bryant.aot.util;

import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	private JSONUtil(){}
	
	/**
	 * 透過jackson將map轉成object
	 * @param fromMap
	 * @param toClaas
	 * @return
	 * @throws Exception
	 */
	public static <T> T convertMapToObject(Map<String,String> fromMap, Class<T> toClaas) throws Exception {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		T obj = mapper.convertValue(fromMap, toClaas);
		
		return null!=obj ? obj : null;
	}
	
	/**
	 * 透過jackson將String轉成object
	 * @param fromMap
	 * @param toClaas
	 * @return
	 * @throws Exception  
	 */
	public static <T> T convertStringToObject(String jsonString, Class<T> toClaas) throws Exception {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		T obj = mapper.readValue(jsonString, toClaas);
		
		return null!=obj ? obj : null;
	}
	

	/**
	 * 透過jackson將Object轉成object
	 * @param fromMap
	 * @param toClaas
	 * @return
	 * @throws Exception
	 */
	public static String convertObjectToJsonString(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(object);
		
		return jsonStr;
	}
	
}
