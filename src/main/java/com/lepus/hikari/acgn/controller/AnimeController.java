package com.lepus.hikari.acgn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.acgn.enums.Season;
import com.lepus.hikari.acgn.enums.SerialState;
import com.lepus.hikari.acgn.service.AnimeService;
import com.lepus.hikari.framework.build.BaseController;
import com.lepus.hikari.framework.build.Page;
import com.lepus.hikari.framework.utils.PinyinUtils;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Controller
public class AnimeController extends BaseController{
	
	@Resource
	protected AnimeService animeService;
	
	@RequestMapping("/anime/cross.ajax")
	public void corss(HttpServletResponse resp){
		resp.addHeader("Access-Control-Allow-Origin", "*");
		try {
			List<Anime> list = new ArrayList<Anime>();
			for(int i=0, len=3; i<len; i++){
				Anime anime = new Anime();
				anime.setId(String.valueOf(i));
				anime.setName("name" + i);
				list.add(anime);
			}
			resp.getOutputStream().write(new Gson().toJson(list).getBytes());
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/anime/trans.do")
	@ResponseBody
	public Object trans(){
		List<Anime> list = animeService.findList(null);
		for(Anime a : list){
			a.setPre(PinyinUtils.getPre(a.getName()));
			animeService.saveOrUpdate(a);
		}
		return getSuccessResponse("", null);
	}
	
	@RequestMapping("/anime/list.go")
	public Object go(ModelMap modelMap, HttpServletRequest req, Page<Anime> page){
		Map<String, String> params = getInterceptoredParams(req);
		params.put("s_order_desc_updateTime", "order");
//		params.put("s_order_asc_pre", "order");
		page = animeService.findPage(params, page);
		modelMap.addAttribute("page", page);
		return "anime-list.jsp";
	}
	
	@RequestMapping("/anime/edit.go")
	@ResponseBody
	public Object editGo(ModelMap modelMap, String id){
		Anime anime = animeService.fetch(id, true);
		return getSuccessResponse("", anime);
	}
	
	@RequestMapping("/anime/edit.do")
	@ResponseBody
	public Object editDo(Anime anime){
		animeService.saveOrUpdate(anime);
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
			List<Anime> list = new ArrayList<Anime>();
			while((line = br.readLine()) != null){
				resolve0(line, list);
			}
			animeService.save(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSuccessResponse("", null);
	}
	private void resolve0(String line, List<Anime> list){
		String[] arr = line.split("-");
		if(arr != null && arr.length > 1){
			Anime anime = new Anime();
			anime.setName(arr[0]);
			anime.setCurr(Integer.parseInt(arr[1]));
			anime.setTotal(anime.getCurr());
			anime.setSeason(Season.S.codeInt());
			if(arr.length > 2){
				Season season = Season.parse(arr[2]);
				anime.setSeason(season.codeInt());
			}
			anime.setSerialState(SerialState.END.codeInt());
			list.add(anime);
		}else{
			System.out.println("error data : " + line);
		}
	}
	
	@RequestMapping("/anime/favo.do")
	@ResponseBody
	public Object favo(String id){
		Anime anime = animeService.changeFavo(id);
		return getSuccessResponse(anime!=null?anime.getFavo()+"":"", null);
	}
	
}
