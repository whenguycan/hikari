package com.lepus.hikari.acgn.enums;

import com.lepus.hikari.framework.utils.EnumsUtils.MyEnum;

/**
 * 
 * @author whenguycan
 * @date 2017年11月4日 下午9:58:57
 */
public enum WatchState implements MyEnum{
	
	O("0", "其他"),
	ING("1", "观看中"),
	END("2", "已补完");
	
	private String code;
	private String text;
	
	private WatchState(String code, String text){
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
