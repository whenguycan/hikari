package com.lepus.hikari.framework.build;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Repository
public class BaseDao extends HibernateDaoSupport{

	protected SessionFactory sessionFactory;
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
}
