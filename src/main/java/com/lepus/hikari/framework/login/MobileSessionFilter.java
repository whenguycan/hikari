package com.lepus.hikari.framework.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class MobileSessionFilter extends OncePerRequestFilter{

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.indexOf("/mobile/") != -1){
			if(uri.indexOf("/login.do") != -1){
				chain.doFilter(request, response);
			}else{
				String user = (String)request.getSession().getAttribute("mobileUser");
				if(StringUtils.isEmpty(user)){
					response.getOutputStream().write("not login".getBytes("UTF-8"));
					response.getOutputStream().flush();
				}else{
					chain.doFilter(request, response);
				}
			}
		}
	}

}
