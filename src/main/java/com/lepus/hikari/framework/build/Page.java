package com.lepus.hikari.framework.build;

import java.util.List;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
public class Page<T> {
	
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int DEFAULT_FIRST_PAGE_NO = 1;

	private int pageNo = DEFAULT_FIRST_PAGE_NO;

	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private int pre = DEFAULT_FIRST_PAGE_NO;
	
	private int after = DEFAULT_FIRST_PAGE_NO + 1;

	private int rows;

	private int pages;

	private List<T> list;
	
	public Page(){
		
	}
	
	public Page(int pageNo){
		setPageNo(pageNo);
	}
	
	public Page(int pageNo, int pageSize){
		setPageNo(pageNo);
		setPageSize(pageSize);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo<1?DEFAULT_FIRST_PAGE_NO:pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize<1?DEFAULT_PAGE_SIZE:pageSize;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getAfter() {
		return after;
	}

	public void setAfter(int after) {
		this.after = after;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
