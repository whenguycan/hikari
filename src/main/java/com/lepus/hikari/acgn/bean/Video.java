package com.lepus.hikari.acgn.bean;

/**
 * 
 * @author wangchenyu
 * @date 2017-12-11
 */
public class Video {
	
	private String sign;
	private String name;
	private String path;
	
	public Video(String sign, String name, String path){
		this.sign = sign;
		this.name = name;
		this.path = path;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

}
