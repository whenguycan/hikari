package com.lepus.hikari.framework.build;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.ServletContextAware;

import com.dc.w.taglib.EnumsConverter;
import com.lepus.hikari.framework.utils.EnumsUtils;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Repository
public class EnumsAware implements ServletContextAware{

	public void setServletContext(ServletContext context) {
		EnumsUtils.load("com.lepus.hikari.acgn.enums");
		EnumsConverter.init(EnumsUtils.allMap());
	}

}
