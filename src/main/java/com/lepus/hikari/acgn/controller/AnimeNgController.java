package com.lepus.hikari.acgn.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.acgn.service.AnimeService;
import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.Page;
import com.lepus.hikari.framework.build.jackson.Filter;
import com.lepus.hikari.framework.build.jackson.Json;
import com.lepus.hikari.model.Checker;

/**
 * 
 * @author wangchenyu
 * @date 2018-1-19
 */
@Controller
public class AnimeNgController extends BaseController {

	@Resource
	AnimeService animeService;
	
	@RequestMapping("/anime/ng/list.ajax")
	@Json(filters = @Filter(type = Anime.class, include = "id,name"))
	public Object list(HttpServletRequest req, HttpServletResponse resp, Page<Anime> page){
		Map<String, String> params = getInterceptoredParams(req);
		params.put("s_order_desc_updateTime", "order");
		return animeService.findPage(params, page);
	}
	
	@RequestMapping("/anime/ng/years.ajax")
	@ResponseBody
	public Object years(HttpServletRequest req, HttpServletResponse resp){
		List<Checker> list = new ArrayList<>();
		list.add(new Checker("", "全部"));
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int currYear = calendar.get(Calendar.YEAR);
		for(int i=0, len=20; i<len; i++){
			list.add(new Checker("" + (currYear - i), "" + (currYear - i)));
		}
		System.out.println("1");
		return list;
	}
	
}
