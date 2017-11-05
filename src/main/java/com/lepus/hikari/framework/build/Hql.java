package com.lepus.hikari.framework.build;

import java.util.Map;

import com.lepus.hikari.framework.utils.StringUtils;

/**
 * 
 * @author whenguycan
 * @date 2017年11月4日 下午11:21:30
 */
public class Hql {

	private String hql;
	
	private Hql(){
		
	}
	
	private Hql(String hqlStr){
		this.hql = hqlStr;
	}
	
	public String toHql(){
		return this.hql;
	}
	
	public static Hql build(String hqlStr, Map<String, String> params){
		if(StringUtils.isBlank(hqlStr))
			return null;
		return new Hql("from Anime where name = :name");
	}
	
	public Hql addOrder(String order){
		this.hql += " " + order;
		return this;
	}
	
}
