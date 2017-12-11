package com.lepus.hikari.acgn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lepus.hikari.acgn.bean.Video;
import com.lepus.hikari.acgn.service.VideoService;

/**
 * 
 * @author wangchenyu
 * @date 2017-12-11
 */
@Controller
public class VideoController {
	
	public static final String[] scanFolders = {"E:\\Downloads"};

	@Resource
	VideoService videoService;
	
	public static final Map<String, Video> map = new HashMap<String, Video>();
	
	@RequestMapping("/video/list.go")
	public Object list(ModelMap modelMap, HttpServletRequest req, String reload){
		if(map.isEmpty() || "true".equals(reload)){
			map.clear();
			map.putAll(videoService.searchVideo(scanFolders));
		}
		modelMap.addAttribute("map", map);
		return "video-list.jsp";
	}
	
	@RequestMapping("/video/play.do")
	public void play(HttpServletResponse resp, String sign){
		Video video = map.get(sign);
		if(video != null){
			
		}
	}
	
}
