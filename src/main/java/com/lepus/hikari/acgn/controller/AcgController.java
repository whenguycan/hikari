package com.lepus.hikari.acgn.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.acgn.bean.Note;
import com.lepus.hikari.acgn.service.AnimeService;
import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.Page;
import com.lepus.hikari.framework.build.jackson.Json;
import com.lepus.hikari.framework.build.jackson.Filter ;

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
	@Json(filters = {
		@Filter(type = Anime.class, include = "id,name"), 
		@Filter(type = Note.class, include = "title")
	})
	public Object page(HttpServletRequest req, Page<Anime> page){
		Object[] arr = new Object[2];
		arr[0] = new Anime();
		arr[1] = new Note();
		return arr;
	}
	
	@RequestMapping("/acg/anime/page2.ajax")
	@ResponseBody
	public Object page2(HttpServletRequest req, Page<Anime> page){
		return animeService.findPage(getInterceptoredParams(req), page);
	}
	
}
