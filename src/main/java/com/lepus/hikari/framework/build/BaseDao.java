package com.lepus.hikari.framework.build;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
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
	
	public <T> Page<T> findPage(Class<T> clazz, Hql hql, Page<T> page){
		Query query = getSession().createQuery(hql.toHql());
		query.setString("name", "jim").setFirstResult(0).setMaxResults(10);
		List list = query.list();
		System.out.println(list.size());
		return null;
	}
	
	public <T> List<T> findList(Class<T> clazz, Hql hql){
		return null;
	}
	
}
