package com.lepus.hikari.acgn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.BaseDao;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Controller
public class AnimeController extends BaseController{
	
	public static void main(String[] args){
		System.out.println(1);
	}
	
	@Resource
	protected BaseDao baseDao; 
	
	@RequestMapping("/anime/list.go")
	public Object go(ModelMap modelMap){
		baseDao.getHibernateTemplate().find("from Anime");
		return "anime-list.jsp";
	}
	
	@RequestMapping("/anime/test.ajax")
	@ResponseBody
	public Object go(String name){
		String str = "其实没成功";
		return getSuccessJson(str, null);
	}
	
}
