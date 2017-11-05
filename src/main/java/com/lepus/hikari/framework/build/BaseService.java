package com.lepus.hikari.framework.build;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * @author whenguycan
 * @date 2017年11月4日 下午11:00:51
 */
@Service
public class BaseService<T> {

	@Resource
	BaseDao dao;
	
}
