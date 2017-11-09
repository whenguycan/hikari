package com.lepus.hikari.acgn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		params.put("s_order_desc_createTime", "order");
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
					anime.setSerialState(i%2 + 1);
					animeService.save(anime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/anime/edit.go")
	public Object editGo(ModelMap modelMap, String id){
		Anime anime = animeService.fetch(id, true);
		modelMap.addAttribute("e", anime);
		return "anime-list-edit.jsp";
	}
	
	@RequestMapping("/anime/edit.do")
	@ResponseBody
	public Object editDo(Anime anime){
//		animeService.saveOrUpdate(anime);
		return getSuccessResponse("", null);
	}
	
	@RequestMapping("/anime/delete.do")
	@ResponseBody
	public Object delete(String id){
		animeService.delete(id);
		return getSuccessResponse("", null);
	}
	
	@RequestMapping("/anime/import.go")
	public Object importGo(){
		return "anime-list-import.jsp";
	}
	
	@RequestMapping("/anime/import.do")
	@ResponseBody
	public Object importDo(@RequestParam("importFile")MultipartFile file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSuccessResponse("", null);
	}
	
}
