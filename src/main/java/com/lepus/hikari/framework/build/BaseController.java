package com.lepus.hikari.framework.build;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
public class BaseController {
	
	protected Gson gson = new Gson();
	
	protected String getSuccessJson(String msg, Object data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("msg", msg);
		map.put("data", data);
		return gson.toJson(map);
	}
	
	protected String getFailedJson(String msg, Exception e){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("msg", msg);
		map.put("e", e!=null?e.getStackTrace():null);
		return gson.toJson(map);
	}

}
