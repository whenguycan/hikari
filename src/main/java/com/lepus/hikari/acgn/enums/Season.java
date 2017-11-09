package com.lepus.hikari.acgn.enums;

import com.lepus.hikari.framework.utils.EnumsUtils.MyEnum;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-9
 */
public enum Season implements MyEnum{
	
	INIT("0", "初始化"),
	S("1", "本编"),
	OVA("2", "ova"),
	OAD("3", "oad"),
	SP("4", "sp"),
	WEB("5", "web"),
	EX("6", "ex"),
	FILM("99", "剧场版");
	
	private String code;
	private String text;
	
	private Season(String code, String text){
		this.code = code;
		this.text = text;
	}
	
	@Override
	public String code() {
		return this.code;
	}

	@Override
	public String text() {
		return this.text;
	}

	@Override
	public int codeInt() {
		return Integer.parseInt(this.code);
	}

}