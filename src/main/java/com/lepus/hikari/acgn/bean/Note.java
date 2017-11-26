package com.lepus.hikari.acgn.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lepus.hikari.framework.build.BaseEntity;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Entity
@Table(name = "t_e_note")
public class Note extends BaseEntity{
	private static final long serialVersionUID = -5336634250956596682L;

	@Column(columnDefinition = "varchar(255) not null")
	private String title;
	
	@Column(columnDefinition = "double not null")
	private double ran = Math.random();
	
	@Column(columnDefinition = "datetime not null", updatable = false)
	private Date createTime = new Date();

	@Column(columnDefinition = "datetime not null", updatable = true)
	private Date updateTime = new Date();
	
	public Note(){
		
	}
	
	public Note(String title){
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public double getRan() {
		return ran;
	}

	public void setRan(double ran) {
		this.ran = ran;
	}
	
}
