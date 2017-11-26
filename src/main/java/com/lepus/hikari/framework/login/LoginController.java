package com.lepus.hikari.framework.login;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lepus.hikari.framework.build.BaseController;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Controller
public class LoginController extends BaseController{
	
	private static final Set<String> tokenSet = new HashSet<String>();

	@RequestMapping("/mobile/login.do")
	@ResponseBody
	public Object login(HttpServletRequest req, HttpServletResponse resp, String username, String password){
		if("jim".equals(username) && "123".equals(password)){
			String token = UUID.randomUUID().toString();
			tokenSet.add(token);
			return getSuccessResponse("login success", token);
		}
		return getSuccessResponse("error username and password", null);
	}
	
	@RequestMapping("/mobile/sub/login.do")
	@ResponseBody
	public Object login(HttpServletRequest req, HttpServletResponse resp, String token){
		if(token != null && tokenSet.contains(token)){
			req.getSession().setAttribute("mobileUser", token);
			return getSuccessResponse("sub login success", null);
		}
		return getSuccessResponse("error login token", null);
	}
	
	@RequestMapping("/mobile/sub/test.do")
	@ResponseBody
	public Object test(HttpServletRequest req, HttpServletResponse resp){
		return getSuccessResponse("lalala", null);
	}
	
}
