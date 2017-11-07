package com.lepus.hikari.acgn.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.acgn.service.AnimeService;
import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.Page;
import com.lepus.hikari.framework.utils.StringUtils;

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
	public Object go(ModelMap modelMap, HttpServletRequest req, Page<Anime> page){
		String size = req.getParameter("size");
		init(size);
		Map<String, String> params = getInterceptoredParams(req);
		page = animeService.findPage(params, page);
		modelMap.addAttribute("page", page);
		return "anime-list.jsp";
	}
	private void init(String size){
		if(StringUtils.isNotBlank(size)){
			try {
				int x = Integer.parseInt(size);
				for(int i=0; i<x; i++){
					Anime anime = new Anime();
					anime.setName("Anime" + Math.random());
					anime.setYear(2016);
					anime.setMonth(i);
					animeService.save(anime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
