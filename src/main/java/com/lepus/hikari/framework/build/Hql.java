package com.lepus.hikari.framework.build;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author whenguycan
 * @date 2017年11月6日 下午8:59:27
 */
public class Hql {
	
	private String hql;
	private Set<Order> orders = new HashSet<Order>();

	private Hql(){}
	
	public String getQueryHql(){
		String order = "";
		for(Order o : orders){
			order += "," + o.field + " " + o.order;
		}
		if(this.hql.matches("(( order by )|( ORDER BY ))+")){
			return this.hql + order;
		}else{
			if(order.length() > 1)
				return this.hql + " order by " + order.substring(1);
			return this.hql;
		}
	}
	
	public String getCountHql(){
		return "select count(*) " + this.hql;
	}
	
	/**
	 * @param hqlStr 原生hql
	 */
	public static Hql init(String originHql){
		if(originHql == null || "".equals(originHql))
			return null;
		
		Hql h = new Hql();
		h.hql = originHql;
		return h;
	}
	
	/**
	 * @param params 由特定规则解析的参数map
	 */
	public static Hql init(Class<?> clazz, Map<String, String> params){
		return null;
	}
	
	public Hql addOrder(Order order){
		orders.add(order);
		return this;
	}
	
	public static class Order{
		private Order(){}
		private Order(String field, String order){
			this.field = field;
			this.order = order;
		}
		private String field;
		private String order;
		public static Order asc(String field){
			return new Order(field, "asc");
		}
		public static Order desc(String field){
			return new Order(field, "desc");
		}
	}
	
	public static void main(String[] args){
		System.out.println(" order by ");
	}
	
}
