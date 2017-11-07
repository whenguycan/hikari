package com.lepus.hikari.framework.build;

import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-7
 */
public class ParmasInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		resolveParams(request);
		return true;
	}
	private void resolveParams(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		for(Entry<String, String[]> entry : params.entrySet()){
			String pattern = entry.getKey();
			String[] arr = pattern.split("_");
			if(arr != null && arr.length != 0){
				if("sa".equalsIgnoreCase(arr[0])){
					String[] valueArr = entry.getValue();
					if(valueArr != null && valueArr.length != 0)
						request.setAttribute(pattern, valueArr[0]);
				}
			}
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}
