package com.lepus.hikari.framework.build;

import java.util.List;

import org.hibernate.Session;
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
	
	public void test(){
		int a = getSession().createQuery("from Anime a left join a.group agroup left join agroup.text agrouptext").list().size();
		int b = getSession().createQuery("from Anime a left join a.group agroup left join a.group.text agrouptext").list().size();
		System.out.println(a + "/" + b);
	}
	
	public <T> Page<T> findPage(Hql hql, Page<T> page){
		Session session = getSession();
		int count = ((Number)session.createQuery(hql.getCountHql()).uniqueResult()).intValue();
		int first = (page.getPageNo() - 1) * page.getPages();
		List<T> list = (List<T>)session.createQuery(hql.getHql()).setFirstResult(first).setMaxResults(page.getPageSize()).list();
		page.setList(list);
		page.setRows(count);
		page.setPages(count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1);
		page.setPre(page.getPageNo()==1?1:page.getPageNo()-1);
		page.setAfter(page.getPageNo()<page.getPages()?page.getPageNo()+1:page.getPages());
		session.close();
		return page;
	}
	
}
