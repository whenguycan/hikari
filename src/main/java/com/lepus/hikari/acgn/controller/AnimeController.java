package com.lepus.hikari.acgn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.BaseDao;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Controller
public class AnimeController extends BaseController{
	
	@Resource
	private BaseDao baseDao;
	
	@RequestMapping("/anime/list.go")
	public Object go(ModelMap modelMap){
		List<?> list = baseDao.getHibernateTemplate().find("from Anime");
		System.out.println(list.size());
		System.out.println(gson.toJson(list));
		for(Object o : list){
			if(o instanceof Object[]){
				System.out.println(((Object[])o)[0]);
			}
		}
		return new ModelAndView("/anime/list.jsp");
	}
	
	@RequestMapping("/anime/test.ajax")
	@ResponseBody
	public Object go(String name){
		
		return "其实没成功";
	}
	
}
