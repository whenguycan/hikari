package com.lepus.hikari.acgn.service;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.framework.build.BaseService;
import com.lepus.hikari.framework.utils.StringUtils;

@Repository
public class AnimeService extends BaseService<Anime>{
	
	public Anime saveOrUpdate(Anime e){
		if(StringUtils.isBlank(e.getId())){
			return save(e);
		}else{
			Anime origin = fetch(e.getId(), false);
			if(origin == null){
				return null;
			}else{
				origin.setName(e.getName());
				origin.setCurr(e.getCurr());
				origin.setTotal(e.getTotal());
				origin.setSerialState(e.getSerialState());
				origin.setSeason(e.getSeason());
				origin.setLink(e.getLink());
				origin.setUpdateTime(new Date());
				origin.calWatchState();
				return dao.update(origin);
			}
		}
	}
	
	public Anime changeFavo(String id){
		Anime origin = fetch(id, false);
		if(origin != null){
			origin.setFavo(origin.getFavo() ^ 1);
			return dao.update(origin);
		}
		return null;
	}
	
}