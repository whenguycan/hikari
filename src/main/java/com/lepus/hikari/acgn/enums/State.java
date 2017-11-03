package com.lepus.hikari.acgn.enums;

import com.lepus.hikari.framework.utils.EnumsUtils.MyEnum;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
public enum State implements MyEnum{
	
	A("1", "A"),
	B("2", "B");
	
	private String code;
	private String text;
	
	private State(String code, String text){
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
