package com.lepus.hikari.acgn.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.acgn.service.AnimeService;
import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.Hql;
import com.lepus.hikari.framework.build.Page;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Controller
public class AnimeController extends BaseController{
	
	@Resource
	protected AnimeService animeService;
	
	@RequestMapping("/anime/list.go")
	public Object go(ModelMap modelMap){
		Page<Anime> page = new Page<Anime>();
		animeService.findPage(page, Hql.init("from Anime a"));
		return "anime-list.jsp";
	}
	
	public static void main(String[] args){
		System.out.println(1);
	}
	
}
