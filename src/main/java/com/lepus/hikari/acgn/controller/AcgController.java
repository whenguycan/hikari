package com.lepus.hikari.acgn.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.acgn.service.AnimeService;
import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.Page;

/**
 * 
 * @author wangchenyu
 * @date 2017-12-21
 */
@Controller
public class AcgController extends BaseController{
	
	@Resource
	AnimeService animeService;

	@RequestMapping("/acg/index.go")
	public Object index(){
		return "acg.jsp";
	}
	
	@RequestMapping("/acg/anime/page.ajax")
	@ResponseBody
	public Object page(HttpServletRequest req, Page<Anime> page){
		return animeService.findPage(getInterceptoredParams(req), page);
	}
	
}
