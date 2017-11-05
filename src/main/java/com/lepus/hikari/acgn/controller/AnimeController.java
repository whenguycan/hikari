package com.lepus.hikari.acgn.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.BaseDao;
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
	protected BaseDao baseDao; 
	
	@RequestMapping("/anime/list.go")
	public Object go(ModelMap modelMap){
		baseDao.findPage(Anime.class, Hql.build("x", null), new Page<Anime>());
		return "anime-list.jsp";
	}
	
}
