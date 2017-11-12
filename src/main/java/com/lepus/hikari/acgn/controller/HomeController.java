package com.lepus.hikari.acgn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-8
 */
@Controller
public class HomeController{
	
	@RequestMapping("/home.go")
	public Object home(ModelMap modelMap){
		return "home.jsp";
	}

}
