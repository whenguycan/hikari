package com.lepus.hikari.framework.build;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * @author whenguycan
 * @date 2017年11月4日 下午11:00:51
 */
@Service
public class BaseService<T extends BaseEntity> {

	@Resource
	BaseDao dao;
	
	private Class<T> clazz;
	
	public BaseService(){
		Type type = getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType){
			Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
			this.clazz = (Class<T>)actualTypeArguments[0];
		}else{
			this.clazz = (Class<T>)type;
		}
	}
	
	public T save(T t){
		return dao.save(t);
	}
	
	public T update(T t){
		return dao.update(t);
	}
	
	public void delete(T t){
		dao.delete(t);
	}
	
	public void delete(String id){
		dao.delete(clazz, id);
	}
	
	public List<T> findList(Map<String, String> params){
		return dao.findList(Hql.build(clazz, params));
	}
	
	public Page<T> findPage(Map<String, String> params, Page<T> page){
		return dao.findPage(Hql.build(clazz, params), page);
	}
	
}
