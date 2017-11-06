package com.lepus.hikari.framework.build;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lepus.hikari.framework.utils.StringUtils;

/**
 * 
 * @author whenguycan
 * @date 2017年11月4日 下午11:21:30
 */
public class Hql {
	
	public static enum Operator{
		eq("="),lt("<"),le("<="),gt(">"),ge(">="),ne("<>"),like("like"),likep("like"),likee("like"),order("order");
		private String oper;
		private Operator(String oper){
			this.oper = oper;
		}
		public static Operator convert(String name){
			Operator[] arr = Operator.values();
			for(Operator e : arr){
				if(e.name().equalsIgnoreCase(name)){
					return e;
				}
			}
			return null;
		}
	}
	
	public static enum Type{
		i,s,asc,desc;
		public static Type convert(String name){
			Type[] arr = Type.values();
			for(Type e : arr){
				if(e.name().equalsIgnoreCase(name)){
					return e;
				}
			}
			return null;
		}
	}
	
	public static class Order{
		private String field;
		private String order;
		private Order(){}
		private Order(String field, String order){
			this.field = field;
			this.order = order;
		}
		public static Order asc(String field){
			return StringUtils.isBlank(field)?null:new Order(field, "asc");
		}
		public static Order desc(String field){
			return StringUtils.isBlank(field)?null:new Order(field, "desc");
		}
	}
	
	private String hql;
	private Set<Order> orders = new HashSet<Order>();
	private String alias;
	
	private Hql(){
		
	}
	
	private Hql(String hql, String alias, Set<Order> orders){
		this.hql = hql;
		this.alias = alias;
		this.orders.addAll(orders);
	}
	
	public String getCountHql(){
		return "select count(" + alias + ") " + this.hql;
	}
	
	public String getHql(){
		
		return this.hql;
	}
	
	/**
	 * @param params 
	 * 1、搜索条件以's_'或'sa_'开头，对于'sa_'开头的条件会在拦截器中自动加载到新一次的request中
	 * 2、其余参数以'_'分隔
	 * 3、参数1：操作符，可选范围：eq,lt,le,gt,ge,ne,like,likep,likee,order(order为特有)
	 * 4、参数2：数据类型，可选范围：i,s,asc,desc(asc和desc是order的特有)
	 * 5、参数3：alias标识，也是以'_'分隔，依照特定规则生成alias的左条件
	 * @param where 原生标准hql的where条件，不带where关键字
	 */
	public static Hql build(Class<?> clazz, String where, Map<String, String> params){
		if(clazz == null)
			return null;
		String hql = "";
		Set<String> joinSet = new HashSet<String>();
		Set<Order> orderSet = new HashSet<Order>();
		String and = "";
		String join = "";
		String className = clazz.getName();
		String entityName = className.substring(className.lastIndexOf(".") + 1);
		String rootAlias = entityName.substring(0, 1).toLowerCase();
		hql += "from " + entityName + " " + rootAlias;
		if(params != null && params.size() != 0){
			for(Entry<String, String> entry : params.entrySet()){
				String key = entry.getKey();
				String val = entry.getValue();
				and += resolve(key, val, rootAlias, orderSet, joinSet);
			}
			for(String j : joinSet){
				join += " left join fetch " + rootAlias + j.replace("_", ".") + " " + ;
			}
		}
		if(StringUtils.isNotBlank(where)){
			
		}
		//把第一个and换成where
		return new Hql(hql, rootAlias, orderSet);
	}
	
	private static String resolve(String key, String val, String rootAlias, Set<Order> orderSet, Set<String> joinSet){
		String[] arr = key.split("_");
		if(arr != null && arr.length > 3){
			String operator = arr[1];
			String type = arr[2];
			String alias = "";
			for(int i=0,len=arr.length;i<len;i++){
				alias += "_" + arr[i];
			}
			joinSet.add(alias);
			Type t = Type.convert(type);
			if(t == Type.asc){
				orderSet.add(Order.asc(key));
			}else if(t == Type.desc){
				orderSet.add(Order.desc(key));
			}
			return fix(operator, type, val, alias, rootAlias);
		}else{
			System.out.println("error exps ignore : " + key + " = " + val);
			return "";
		}
	}
	private static String fix(String operator, String type, String val, String alias, String rootAlias){
		String and = " and " + rootAlias + alias.replace("_", ".");
		Operator oper = Operator.convert(operator);
		Type tp = Type.convert(type);
		if(oper == null){
			System.out.println("ignore error operator : " + operator);
		}else if(oper != Operator.order){
			if(oper.name().indexOf("like") != -1){
				if(oper == Operator.likep){
					and += " like '" + val + "%'";
				}else if(oper == Operator.likee){
					and += " like '%" + val + "'";
				}else{
					and += " like '%" + val + "%'";
				}
			}else{
				if(tp == Type.i){
					and += " " + oper.oper + " " + val;
				}else if(tp == Type.s){
					and += " " + oper.oper + "'" + val + "'";
				}else{
					System.out.println("error search type : " + type);
				}
			}
			return and;
		}
		return "";
	}
	
	public Hql addOrder(Order order){
		orders.add(order);
		return this;
	}
	
}
