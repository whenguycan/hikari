package com.lepus.hikari.framework.build.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * 
 * @author wangchenyu
 * @date 2017-12-23
 */
public class CustomJsonSerializer {

	static final String DYNC_INCLUDE = "DYNC_INCLUDE";
	static final String DYNC_EXCLUDE = "DYNC_EXCLUDE";
	
	ObjectMapper mapper = new ObjectMapper();
	
	@JsonFilter(DYNC_INCLUDE)
	interface DynamicInclude{}
	
	@JsonFilter(DYNC_EXCLUDE)
	interface DynamicExclude{}
	
	public void filter(Class<?> clazz, String include, String exclude){
		if(clazz == null)
			return;
		if(include != null && include.length() != 0){
			mapper.setFilterProvider(new SimpleFilterProvider().addFilter(DYNC_INCLUDE, SimpleBeanPropertyFilter.filterOutAllExcept(include.split(","))));
			mapper.addMixIn(clazz, DynamicInclude.class);
		}
		if(exclude != null && exclude.length() != 0){
			mapper.setFilterProvider(new SimpleFilterProvider().addFilter(DYNC_EXCLUDE, SimpleBeanPropertyFilter.serializeAllExcept(exclude.split(","))));
			mapper.addMixIn(clazz, DynamicExclude.class);
		}
	}
	
	public String toJson(Object value) throws JsonProcessingException{
		return mapper.writeValueAsString(value);
	}
	
}
