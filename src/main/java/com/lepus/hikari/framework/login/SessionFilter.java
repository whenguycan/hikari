package com.lepus.hikari.framework.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter{

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//		String uri = request.getRequestURI();
//		if(uri.indexOf("/login.do") != -1 || uri.indexOf("/mobile/") != -1){
//			chain.doFilter(request, response);
//		}
		chain.doFilter(request, response);
	}

}
