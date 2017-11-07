package com.lepus.hikari.framework.build;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.lepus.hikari.acgn.bean.Anime;

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
	
	public <T> T save(T t){
		getHibernateTemplate().save(t);
		return t;
	}
	
	public <T> T update(T t){
		getHibernateTemplate().update(t);
		return t;
	}
	
	public <T> void delete(T t){
		getHibernateTemplate().delete(t);
	}
	
	public <T> void delete(Class<T> clazz, String id){
		T t = getHibernateTemplate().get(clazz, id);
		getHibernateTemplate().delete(t);
	}
	
	public <T> List<T> findList(Hql hql){
		Session session = getSession();
		List<T> list = session.createQuery(hql.getQueryHql()).list();
		session.close();
		return list;
	}
	
	public <T> Page<T> findPage(Hql hql, Page<T> page){
		Session session = getSession();
		int count = ((Number)session.createQuery(hql.getCountHql()).uniqueResult()).intValue();
		int first = (page.getPageNo() - 1) * page.getPages();
		List<T> list = session.createQuery(hql.getQueryHql()).setFirstResult(first).setMaxResults(page.getPageSize()).list();
		page.setList(list);
		page.setRows(count);
		page.setPages(count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1);
		page.setPre(page.getPageNo()==1?1:page.getPageNo()-1);
		page.setAfter(page.getPageNo()<page.getPages()?page.getPageNo()+1:page.getPages());
		session.close();
		return page;
	}
	
}
