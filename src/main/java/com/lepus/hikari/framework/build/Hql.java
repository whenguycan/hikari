package com.lepus.hikari.framework.build;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.lepus.hikari.acgn.bean.Anime;

/**
 * 
 * @author whenguycan
 * @date 2017年11月6日 下午8:59:27
 */
public class Hql {
	
	public static void main(String[] args){
		Map<String, String> params = new HashMap<String, String>();
		params.put("s_eq_i_curr", "1");
		params.put("sa_slike_s_group_name", "jim");
//		Hql hql = Hql.init(" from Anime anime").addOrder(Order.desc("group.name"));
		Hql hql = Hql.init(Anime.class, params).addOrder(Order.desc("group.name"));
		System.out.println(hql.getQueryHql());
	}
	
	private String hql;
	private String rootAlias = "";
	private Set<Order> orders = new HashSet<Order>();

	private Hql(){}
	
	public String getQueryHql(){
		String order = "";
		for(Order o : orders){
			order += "," + o.field + " " + o.order;
		}
		if(this.hql.matches("(( order by )|( ORDER BY ))+")){
			return  this.hql + this.rootAlias + order;
		}else{
			if(order.length() > 1)
				return  this.hql + " order by " + this.rootAlias + order.substring(1);
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
	 * @param params 由特定规则解析的参数map，最多支持3层嵌套条件
	 */
	public static Hql init(Class<?> clazz, Map<String, String> params){
		String className = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
		String rootAlias = className.toLowerCase();
		Set<String> joinSet = new HashSet<String>();
		Set<Order> orderSet = new HashSet<Order>();
		String and = "";
		for(Entry<String, String> entry : params.entrySet()){
			String pattern = entry.getKey();
			String value = entry.getValue();
			if(isSearcherPattern(pattern)){
				String[] arr = pattern.split("_");
				String searcher = arr[0];
				String operator = arr[1];
				String dataType = arr[2];
				String alias = pattern.substring(searcher.length() + operator.length() + dataType.length() + 3);
				joinSet.add(alias);
				and += resolveAnd(rootAlias, alias, operator, value, dataType, orderSet);
			}else{
				System.out.println("error searcher pattern : " + pattern);
			}
		}
		String hqlStr = "from " + className + " " + rootAlias;
		hqlStr += resolveJoin(rootAlias, joinSet);
		hqlStr += resolveAnd(and);
		Hql h = new Hql();
		h.rootAlias = rootAlias;
		h.hql = hqlStr;
		h.orders.addAll(orderSet);
		return h;
	}
	private static String resolveJoin(String rootAlias, Set<String> joinSet){
		String join = "";
		Set<String> aliasSet = new HashSet<String>();
		for(String s : joinSet){
			String[] arr = s.split("_");
			if(arr.length > 1){
				for(int i=0, len=arr.length; i<len-1; i++){
					String alias = rootAlias;
					for(int j=0; j<i; j++){
						alias += arr[j];
					}
					alias += "." + arr[i];
					aliasSet.add(alias);
				}
			}
		}
		for(String s : aliasSet){
			join += " left join " + s + " " + s.replace(".", "");
		}
		return join;
	}
	//s_eq_i_group_text_name = jim
	//sa_order_asc_jim = sa_order_asc_jim
	private static String resolveAnd(String rootAlias, String alias, String operator, String value, String dataType, Set<Order> orderSet){
		String and = AND_LINKER;
		String[] arr = alias.split("_");
		String left = "";
		String right = "";
		if(arr.length == 1){
			right = arr[0];
		}else{
			for(int i=0,len=arr.length;i<len-1;i++){
				left += arr[i];
			}
			right = arr[arr.length - 1];
		}
		String key = rootAlias + left + "." + right;
		Operator o = Operator.convert(operator);
		DataType t = DataType.convert(dataType);
		if(o.name().indexOf(Operator.like.name()) != -1){
			if(o == Operator.slike){
				and += key + " like '" + value + "%'";
			}else if(o == Operator.likes){
				and += key + " like '%" + value + "'";
			}else{
				and += key + " like '%" + value + "%'";
			}
		}else if(o == Operator.order){
			if(t == DataType.asc){
				
			}else{
				
			}
		}else{
			if(t == DataType.i){
				and += key + " " + o.linker + " " + value;
			}else if(t == DataType.s){
				and += key + " " + o.linker + " '" + value + "'";
			}
		}
		return and;
	}
	private static final String AND_LINKER = " and ";
	private static String resolveAnd(String and){
		return and==null||and.length()==0?"":" where " + and.substring(AND_LINKER.length());
	}
	
	private static boolean isSearcherPattern(String pattern){
		if(pattern == null)
			return false;
		String[] arr = pattern.split("_");
		if(arr == null || arr.length < 3)
			return false;
		if(!"s".equalsIgnoreCase(arr[0]) && !"sa".equalsIgnoreCase(arr[0]))
			return false;
		Operator o = Operator.convert(arr[1]);
		if(o == null)
			return false;
		DataType d = DataType.convert(arr[2]);
		if(d == null)
			return false;
		return true;
	}
	
	public Hql addOrder(Order order){
		orders.add(order);
		return this;
	}
	
	private enum Operator{
		eq("="),
		ne("<>"),
		lt("<"),
		le("<="),
		gt(">"),
		ge(">="),
		like("like"),
		slike("like"),
		likes("like"),
		order("order");
		private String linker;
		private Operator(String linker){
			this.linker = linker;
		}
		public static Operator convert(String name){
			if(name == null)
				return null;
			Operator[] arr = Operator.values();
			for(Operator e : arr){
				if(e.name().equalsIgnoreCase(name))
					return e;
			}
			return null;
		}
	}
	
	private enum DataType{
		i,s,asc,desc;
		public static DataType convert(String name){
			if(name == null)
				return null;
			DataType[] arr = DataType.values();
			for(DataType e : arr){
				if(e.name().equalsIgnoreCase(name))
					return e;
			}
			return null;
		}
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
	
}
