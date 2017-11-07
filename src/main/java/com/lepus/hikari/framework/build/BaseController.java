package com.lepus.hikari.framework.build;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.lepus.hikari.framework.utils.StringUtils;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
public class BaseController {
	
	protected Gson gson = new Gson();
	
	protected Map<String, String> getInterceptoredParams(HttpServletRequest req){
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> e = req.getParameterNames();
		while(e.hasMoreElements()){
			String pattern = e.nextElement();
			String[] arr = pattern.split("_");
			if("s".equalsIgnoreCase(arr[0]) || "sa".equalsIgnoreCase(arr[0])){
				String[] valueArr = req.getParameterValues(pattern);
				if(valueArr != null && valueArr.length != 0 && StringUtils.isNotBlank(valueArr[0])){
					params.put(pattern, valueArr[0]);
				}
			}
		}
		return params;
	}
	
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
