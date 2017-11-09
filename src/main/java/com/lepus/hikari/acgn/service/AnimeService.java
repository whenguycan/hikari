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
				origin.setExt(anime.getExt());
				origin.setYear(anime.getYear());
				origin.setMonth(anime.getMonth());
				origin.setCurr(anime.getCurr());
				origin.setTotal(anime.getTotal());
				origin.setSerialState(anime.getSerialState());
				origin.setUpdateTime(new Date());
				return dao.update(anime);
			}
		}
	}
	
}