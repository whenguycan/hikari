package com.lepus.hikari.acgn.service;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.lepus.hikari.acgn.bean.Anime;
import com.lepus.hikari.framework.build.BaseService;
import com.lepus.hikari.framework.utils.StringUtils;

@Repository
public class AnimeService extends BaseService<Anime>{
	
	public Anime saveOrUpdate(Anime anime){
		if(StringUtils.isBlank(anime.getId())){
			return save(anime);
		}else{
			Anime origin = fetch(anime.getId(), false);
			if(origin == null){
				return null;
			}else{
				origin.setName(anime.getName());
				origin.setCurr(anime.getCurr());
				origin.setTotal(anime.getTotal());
				origin.setSerialState(anime.getSerialState());
				origin.setSeason(anime.getSeason());
				origin.setLink(anime.getLink());
				origin.setUpdateTime(new Date());
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