package com.lepus.hikari.acgn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lepus.hikari.acgn.bean.Note;
import com.lepus.hikari.acgn.service.NoteService;
import com.lepus.hikari.framework.build.BaseController;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Controller
public class NoteController extends BaseController{
	
	@Resource
	protected NoteService noteService;
	
	@RequestMapping("/note/random.go")
	public Object go(ModelMap modelMap, HttpServletRequest req){
		List<Note> list = noteService.findRandom();
		modelMap.addAttribute("list", list);
		return "note-random.jsp";
	}
	
	@RequestMapping("/note/import.do")
	@ResponseBody
	public Object importDo(@RequestParam("importFile")MultipartFile file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line = null;
			List<Note> list = new ArrayList<Note>();
			while((line = br.readLine()) != null){
				list.add(new Note(line));
			}
			noteService.save(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSuccessResponse("", null);
	}
	
}
