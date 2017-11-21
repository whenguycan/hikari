package com.lepus.hikari.acgn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lepus.hikari.acgn.bean.Note;
import com.lepus.hikari.framework.build.BaseService;
import com.lepus.hikari.framework.build.Hql;
import com.lepus.hikari.framework.build.Page;
import com.lepus.hikari.framework.utils.StringUtils;

@Repository
public class NoteService extends BaseService<Note>{
	
	public Note saveOrUpdate(Note e){
		if(StringUtils.isBlank(e.getId())){
			return save(e);
		}else{
			Note origin = fetch(e.getId(), false);
			if(origin == null){
				return null;
			}else{
				origin.setTitle(e.getTitle());
				origin.setUpdateTime(new Date());
				return dao.update(origin);
			}
		}
	}
	
	public List<Note> findRandom(){
		List<Note> list = new ArrayList<Note>();
		int need = 5;
		int max = 50;
		int curr = 0;
		while(curr < max && list.size() < need){
			Hql hql = Hql.build("from Note n where n.ran > " + Math.random() + "order by n.ran");
			Page<Note> page = new Page<Note>(1, 1);
			list.addAll(dao.findPage(hql, page).getList());
			curr++;
		}
		return list;
	}
	
}