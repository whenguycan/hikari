package com.lepus.hikari.framework.build;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * @author whenguycan
 * @date 2017年11月4日 下午11:00:51
 */
@Service
public class BaseService<E> {

	@Resource
	BaseDao dao;
	
	private Class<?> clazz;
	
	public BaseService(){
		Type type = getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType){
			System.out.println(1);
			Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
			this.clazz = (Class<?>)actualTypeArguments[0];
		}else{
			this.clazz = (Class<?>)type;
		}
	}
	
	public <T> Page<T> findPage(Page<T> page, Map<String, String> params){
		Hql hql = Hql.build(clazz, "name like 'j%'", params);
		return dao.findPage(hql, page);
	}
	
}
