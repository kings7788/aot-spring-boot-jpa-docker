//package com.bryant.aot.converter;
//
//import java.io.IOException;
//import java.util.Map;
//
//import javax.persistence.AttributeConverter;
//
//import com.bryant.aot.util.LogUtil;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class HashMapConverter implements AttributeConverter<Map<String, Object>, String>{
//	
//	@Override
//	public String convertToDatabaseColumn(Map<String, Object> attribute) {
//		  String memberInfoJson = null;
//		  //ObjectMapper objectMapper = new ObjectMapper();
//	        try {
//	        	memberInfoJson = objectMapper.writeValueAsString(attribute);
//	        } catch (final JsonProcessingException e) {
//	            LogUtil.log.error("JSON writing error", e);
//	        }
//	 
//	        return memberInfoJson;
//	}
//
//	@Override
//	public Map<String, Object> convertToEntityAttribute(String dbData) {
//		//ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> memberInfo = null;
//        try {
//        	memberInfo = objectMapper.readValue(dbData, Map.class);
//        } catch (final IOException e) {
//        	LogUtil.log.error("JSON reading error", e);
//        }
// 
//        return memberInfo;
//	}
//
//}
