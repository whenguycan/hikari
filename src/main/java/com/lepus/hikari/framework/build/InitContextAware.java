package com.lepus.hikari.framework.build;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ServletContextAware;

import com.lepus.hikari.framework.utils.EnumsConverter;
import com.lepus.hikari.framework.utils.EnumsUtils;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-8
 */
@Repository
public class InitContextAware implements ServletContextAware, ApplicationContextAware{

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		
	}

	@Override
	public void setServletContext(ServletContext context) {
		initEnums(context);
		initContextPath(context);
	}
	private void initEnums(ServletContext context){
		EnumsUtils.load("com.lepus.hikari.acgn.enums");
		for(String tag : EnumsUtils.allMap().keySet()){
			context.setAttribute(tag, EnumsUtils.generateSelect(tag, new String[]{"0"}));
		}
		EnumsConverter.init(EnumsUtils.allMap());
	}
	private void initContextPath(ServletContext context){
		context.setAttribute("root", context.getContextPath());
	}

}
