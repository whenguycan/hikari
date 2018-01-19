package com.lepus.hikari.model;

/**
 * 
 * @author wangchenyu
 * @date 2018-1-19
 */
public class Checker {

	private String name;
	private String text;
	
	public Checker(String name, String text){
		this.name = name;
		this.text = text;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
